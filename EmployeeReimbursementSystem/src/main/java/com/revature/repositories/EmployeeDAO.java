package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

public interface EmployeeDAO {

	
	public Employee findByUsername(String username);
	public int insertEmployeeReimbursement(Reimbursement reim);
	public int insertEmployeeUpdate(Employee e);
	public List<Object> employeeView(int e, int view);
	
}
