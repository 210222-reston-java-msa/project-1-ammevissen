function employeeChoice(){

	console.log("get task choice");
	let choice=document.getElementById('task').value;
	
	console.log(`employee task is ${task}`);

	let userID = sessionStorage.getItem('userId');

	console.log(`the current user is: ${userID}`);

	
	
	switch (choice){
		case "submit a reimbursement request":
			window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeReimbursementRequest.html";
			break;
		case "view personal information":
			window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeInformation.html";
			break;
		case "view requests":
			window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeView.html";
			break;		
	}	
}

function logout(){

		let xhr = new XMLHttpRequest();
		
		xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/logout");
		xhr.send();
		
		sessionStorage.removeItem('userId');
		window.location = "http://localhost:8080/EmployeeReimbursementSystem/";
		
}