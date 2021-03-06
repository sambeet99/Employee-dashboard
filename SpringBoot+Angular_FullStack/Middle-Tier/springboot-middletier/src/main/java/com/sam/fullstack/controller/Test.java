package com.sam.fullstack.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class Test {



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


		HttpHeaders headers = new HttpHeaders();

		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>( headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = null;

		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 



		for (int day=0;day <7;day++) {

			String url = null;
			c.add(Calendar.DATE, 1);
			
			


			String stdt = formatter.format( c.getTime()).replaceAll("/", "-");

			url =  "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=449&date="+stdt;
			System.out.println(stdt+System.lineSeparator());

			result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			


		try {


				Gson gson = new Gson();

				Root root = gson.fromJson(result.getBody(), Root.class);

				List<Centers> centerlist = root.getCenters();

				for (int i =0 ; i<centerlist.size();i++) {

					for (Session sn : centerlist.get(i).getSessions()) 
					{ 
						if(sn.min_age_limit ==45) {
						System.out.println(" centre lsit.."+centerlist.get(i).getName());
						System.out.println("slot found..." + sn.getAvailable_capacity()+" ..Date..."+sn.getDate());
						}

					}




				}


			} catch (Exception e) {
				e.printStackTrace();}






		}


	}


}


