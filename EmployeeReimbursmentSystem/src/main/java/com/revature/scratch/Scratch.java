package com.revature.scratch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.services.EmployeeService;

public class Scratch {

	private static Logger log = Logger.getLogger(Scratch.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		ObjectMapper objectMapper=new ObjectMapper();
//		JacksonTestModel test1=new JacksonTestModel("yellow", "renault");
//		try {
//			objectMapper.writeValue(new File("target/car.jason"), test1);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
//		try {
//			JacksonTestModel car = objectMapper.readValue(json, JacksonTestModel.class);
//			System.out.println(car);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Model 1");
//
//			//e.printStackTrace();
//		}
//		
//		//model names and JSON object must be the same
//		try {
//			JacksonTestModel2 car = objectMapper.readValue(json, JacksonTestModel2.class);
//			System.out.println(car);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Model 2");
//			//e.printStackTrace();
//		}
		
		
		String username="ammevissen";
		String password="p@55w0rd";
		int role=1;
				
		log.info("User attempted to login with username: "+username);
		Employee e=EmployeeService.confirmLogin(username, password, role);

		if (e!=null) {
			
			log.info(username+ " has successfully logged in");
			
		}else {
			log.info(username+ " has not successfully logged in");
		}
		
		
	}

}
