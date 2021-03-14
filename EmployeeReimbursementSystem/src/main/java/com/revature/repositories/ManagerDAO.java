package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public interface ManagerDAO {

	
	
	public List<Object> managerView(int view);
	public List<Object> viewEmployees();
	public int reimApprove(int approve, int e,  int ReimId);
	
}
