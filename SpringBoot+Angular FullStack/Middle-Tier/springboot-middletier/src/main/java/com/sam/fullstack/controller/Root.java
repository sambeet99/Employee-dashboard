package com.sam.fullstack.controller;

import java.util.List;

//import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
 class Session{
 public String session_id;
 public String date;
 public int available_capacity;
 public String getSession_id() {
	return session_id;
}
public void setSession_id(String session_id) {
	this.session_id = session_id;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getAvailable_capacity() {
	return available_capacity;
}
public void setAvailable_capacity(int available_capacity) {
	this.available_capacity = available_capacity;
}
public int getMin_age_limit() {
	return min_age_limit;
}
public void setMin_age_limit(int min_age_limit) {
	this.min_age_limit = min_age_limit;
}
public String getVaccine() {
	return vaccine;
}
public void setVaccine(String vaccine) {
	this.vaccine = vaccine;
}
public List<String> getSlots() {
	return slots;
}
public void setSlots(List<String> slots) {
	this.slots = slots;
}
public int min_age_limit;
 public String vaccine;
 public List<String> slots;
 
 
 
 @Override
 public String toString() {
	 
	 return String.valueOf(available_capacity);
 }
 
 
}

 class Centers{
 public int center_id;
 public String name;
 public String address;
 public String state_name;
 public String district_name;
 public String block_name;
 public int pincode;
 public double lat;
 public int getCenter_id() {
	return center_id;
}
public void setCenter_id(int center_id) {
	this.center_id = center_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getState_name() {
	return state_name;
}
public void setState_name(String state_name) {
	this.state_name = state_name;
}
public String getDistrict_name() {
	return district_name;
}
public void setDistrict_name(String district_name) {
	this.district_name = district_name;
}
public String getBlock_name() {
	return block_name;
}
public void setBlock_name(String block_name) {
	this.block_name = block_name;
}
public int getPincode() {
	return pincode;
}
public void setPincode(int pincode) {
	this.pincode = pincode;
}
public double getLat() {
	return lat;
}
public void setLat(int lat) {
	this.lat = lat;
}
public int getLongi() {
	return longi;
}
public void setLongi(int longi) {
	this.longi = longi;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getFee_type() {
	return fee_type;
}
public void setFee_type(String fee_type) {
	this.fee_type = fee_type;
}
public List<Session> getSessions() {
	return sessions;
}
public void setSessions(List<Session> sessions) {
	this.sessions = sessions;
}
public int longi;
 public String from;
 public String to;
 public String fee_type;
 public List<Session> sessions;
 
 @Override
 public String toString() {
	 
	 /*return (this.getAddress()+"/n"+this.getBlock_name()+"/n"+this.getCenter_id()+"/n"+ this.getDistrict_name()+"/n"+ this.getDistrict_name()+ "/n"+this.getFee_type()+
			 "/n"+this.getFrom()+"/n"+ this.getName()+"/n"+ this.getPincode()+"/n"+this.getState_name()+"/n"+ this.getTo());*/
	 return "foud..."+this.getAddress();
 }
}

public class Root{
 public List<Centers> centers;

public List<Centers> getCenters() {
	return centers;
}

public void setCenters(List<Centers> centers) {
	this.centers = centers;
}
}


