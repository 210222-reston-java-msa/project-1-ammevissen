package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.LoginInfo;
import com.revature.services.EmployeeService;

public class RequestHelperUtil {

	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();

	public static String processEmployeeLogin(HttpServletRequest req) throws IOException { 
		
		
		BufferedReader reader=req.getReader();
		StringBuilder s=new StringBuilder();
	
		//looping through request data until all data is read into StringBuilder s
		String line=reader.readLine();
		while (line!=null) {
			s.append(line);
			line=reader.readLine();
		}
	
		//converting StringBuilder s to String
		String body=s.toString();
		log.info(body);

		return(body);
	}
	
}