package com.revature.services;

import java.util.List;

import com.revature.repositories.ManagerDAO;
import com.revature.repositories.ManagerDAOImpl;

public class ManagerService {

	public static ManagerDAO mDAO=new ManagerDAOImpl();
	
	
	public static List<Object> managerView(int view){
		return mDAO.managerView(view);
	}

	public static List<Object> managerView(int e, int view){
		return EmployeeService.employeeView(e, view);
	}
	
	public static List<Object> viewEmployees(){
		return mDAO.viewEmployees();
	}
	
	public static int approve(int approve, int e,  int ReimId) {
		return mDAO.reimApprove(approve, e, ReimId);
		
	}
}
