package com.sam.fullstack.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.sam.fullstack.exception.ResourceNotFoundException;
import com.sam.fullstack.model.Employee;
import com.sam.fullstack.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping ("/api/v1/")
public class EmployeeController {



	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


	@Autowired
	private EmployeeRepository employeeRepository;

	//get all employess
	@GetMapping("/employees")
	public List<Employee> getAll() {
		return employeeRepository.findAll();

	}

	@GetMapping("/slot")
	public String get() {

		HttpHeaders headers = new HttpHeaders();

		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>( headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = null;

		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		String s = null;

       int range = 97;
		for (int day=0;day <range;day++) {

			String url = null;
			
			c.add(Calendar.DATE, 1);

			String stdt = formatter.format( c.getTime()).replaceAll("/", "-");

			url =  "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=449&date="+stdt;

			result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			Gson gson = new Gson();

			Root root = gson.fromJson(result.getBody(), Root.class);

			List<Centers> centerlist = root.getCenters();

			for (int i =0 ; i<centerlist.size();i++) {

				s= s+"centre name.."+centerlist.get(i).getName()+"...."+stdt+"--"+centerlist.get(i).getBlock_name()+System.lineSeparator();

				for (Session sn : centerlist.get(i).getSessions()) 
				{ 
					if(sn.getAvailable_capacity()>0 ) {
						//s=s+"   slot capacity ..." + sn.getAvailable_capacity()+System.lineSeparator();
						s=s+"     Yes found ...at session id:"+sn.getSession_id()+"   capacity =>"+sn.getAvailable_capacity()+"..min_age_limit.."+sn.getMin_age_limit()+System.lineSeparator()+System.lineSeparator();
					}
					else {
						
						s=s+ "      No slot found for session  " +sn.getSession_id()+System.lineSeparator()+System.lineSeparator();
					}

				}

			}

		}

		return s.substring(4) ;
	}



	//Add employee
	@PostMapping("/employees")
	public Employee addEmplyoee(@RequestBody Employee employee) {

		return employeeRepository.save(employee);
	}

	//get emloyee by Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

		Employee  employee = employeeRepository.findById(id).
				orElseThrow(() ->new ResourceNotFoundException("No Employee of this ID"));

		return ResponseEntity.ok(employee);
	}  


	//update employee - for that need to retrieve employe form DB
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee (@PathVariable Long id , @RequestBody Employee employee){

		Employee  employeeRetrieved = employeeRepository.findById(id).
				orElseThrow(() ->new ResourceNotFoundException("No Employee of this ID"));

		employeeRetrieved.setFirstName(employee.getFirstName());
		employeeRetrieved.setLastName(employee.getLastName());
		employeeRetrieved.setEmailId(employee.getEmailId());

		Employee updatedEmployee=employeeRepository.save(employeeRetrieved);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity deleteEmployee (@PathVariable Long id){
		
		try {
		Employee  employeeRetrieved = employeeRepository.findById(id).
				orElseThrow(() ->new ResourceNotFoundException("No Employee of this ID..Hence cant be deleted.."));
		
		employeeRepository.delete(employeeRetrieved);
		Map<String , Employee> response = new HashMap();
		response.put("deleted", employeeRetrieved);
		
		return ResponseEntity.status(200).body(response);
		
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()) ;       //status(500).(new HashMap<String,String>().put("Issue", e.getMessage()));
		}
		
		
		
	}

}
