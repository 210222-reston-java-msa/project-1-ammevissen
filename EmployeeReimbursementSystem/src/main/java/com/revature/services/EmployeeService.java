package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class EmployeeService {

	public static EmployeeDAO eDAO=new EmployeeDAOImpl();
	
	public static Employee confirmLogin(String username, String password, int role) {
		Employee e=eDAO.findByUsername(username);
		
		if (e!=null && e.getPassword().equals(password) &&e.getRoleId()>=role) {
			return (e);
		}else {
			return null;
		}
		
	}
	
	
	public static int processEmployeeReimbursement(Reimbursement reim) {
		
		return eDAO.insertEmployeeReimbursement(reim);
	}
	
	public static int processEmployeeUpdate(Employee e) {
		
		return eDAO.insertEmployeeUpdate(e);
		
	}
	
	
	public static List<Object> employeeView(int e, int view){
		return eDAO.employeeView(e, view);
	}
	
}

