function employeeReimbursementRequest(){


	//Getting employee information
	console.log("Employee Reimbursement Request");

	let amount=document.getElementById('amount').value;

    let options =document.querySelectorAll('input[name="type"]')
    let type;

	let description=document.getElementById('description').value;


	console.log("Starting Manager Reimbursement Viewing");
	let SesInfo = sessionStorage.getItem('userId');
	
	let author;
	let	firstName;
	let lastName;
	let username;
	let userId;
	let email;
	let role;
	

	

	if (SesInfo === null) {
		window.location = window.location = "http://localhost:8080/EmployeeReimbursementSystem/index.html";;
	} else {
		
		let SesInfoOjb = JSON.parse(SesInfo); 
		e=SesInfoOjb.e;
	
		console.log(SesInfoOjb.userId);
		
		if (SesInfoOjb != null) {
			firstName=e.firstName;
			lastName=e.lastName;
			username=e.username;
			userId=e.userId;
			email=e.email;
			role=e.roleId;
		}
	}
		

	//Getting view choice
	//https://www.javascripttutorial.net/javascript-dom/javascript-radio-button/    
    for (const option of options){
        if (option.checked){
            type=option.value;
            break;
        }
    }

    console.log(`amount: ${amount}`);
    console.log(`type: ${type}`);
    console.log(`description: ${description}`);
	console.log(`author: ${userId}`);
    
	
	//Creating Reimbursement Request template to send information to backend
	let reimbursementRequest={
		userId:userId,
		username:username,
		firstName:firstName,
		lastName:lastName,
		email:email,
		roleId:role,
		amount:amount,
		description:description,
		statusId:1,
		typeId:type
	}
	
	//Creating request object
	console.log("step 1");
	let xhr=new XMLHttpRequest();

	//Creating request completed function
	console.log("step 2");	
	xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

			console.log("heading to employee Home");
            window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeHome.html";
        }
	}

	//Invoking backend method
	console.log("step 3");
    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/employeeReimbursement")

	//Passing information to backend
	console.log("step 4");
    xhr.send(JSON.stringify(reimbursementRequest))
	console.log("Done");
}

//Going to employee home page
function employeeHome(){
	window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeHome.html";
}

//Logging out
function logout(){

		let xhr = new XMLHttpRequest();
		
		xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/logout");
		xhr.send();
		
		sessionStorage.removeItem('userId');
		window.location = "http://localhost:8080/EmployeeReimbursementSystem/";
		
}
