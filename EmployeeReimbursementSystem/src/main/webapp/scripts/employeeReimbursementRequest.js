function employeeReimbursementRequest(){

	console.log("Employee Reimbursement Request");

	let amount=document.getElementById('amount').value;

    let options =document.querySelectorAll('input[name="type"]')
    let type;

	let description=document.getElementById('description').value;


	let SesInfo = sessionStorage.getItem('userId');
	let author;

	if (SesInfo === null) {
		window.location = window.location = "http://localhost:8080/EmployeeReimbursementSystem/index.html";;
	} else {
		
		let SesInfoOjb = JSON.parse(SesInfo); // parse the data that we see == to that attribute
		
		console.log(SesInfoOjb.userId);
		
		if (SesInfoOjb != null) {
			author=SesInfoOjb.userId;
		}
	}
		


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
	console.log(`author: ${author}`);
    
//	ses.setAttribute("userId", e.getUserId());
//	ses.setAttribute("username", e.getUsername());
//	ses.setAttribute("firstName", e.getFirstName());
//	ses.setAttribute("lastName", e.getLastName());
//	ses.setAttribute("email", e.getEmail());
//	ses.setAttribute("roleId", e.getRoleId());

//	int id, double amount, LocalDate submitted, LocalDate resolved, String description, int author,
//			int resolver, int statusId, int typeId

	
	
	
	
	let reimbursementRequest={
		amount:amount,
		description:description,
		author:author,
		statusId:1,
		typeId:type
	}
	
	
	console.log("step 1");
	let xhr=new XMLHttpRequest();

	console.log("step 2");	
	xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

			//console.log("heading to employee Home");
            //window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeHome.html";
        }

        if (this.readyState === 4 && this.status === 204) { // 204 means NO CONTENT FOUND (but connection was made)
			console.log("failed to be accepted, make sure amount is a valid number")
			let error=sessionStorage.getItem('error');
            //console.log("failed to find user");

            //let childDiv = document.getElementById('warningText');
            //childDiv.textContent = "Failed to login!  Username of Password is incorrect"
        }
    }

	console.log("step 3");
    // 3. xhr.open("POST, "http:/localhost:8080/EmployeeDBServlet/url for the loginServlet")
    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/employeeReimbursement")

	console.log("step 4");
    // 4. xhr.send();
    xhr.send(JSON.stringify(reimbursementRequest))
	console.log("Done");
}


function employeeHome(){
	window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeHome.html";
}

function logout(){

		let xhr = new XMLHttpRequest();
		
		xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/logout");
		xhr.send();
		
		sessionStorage.removeItem('userId');
		window.location = "http://localhost:8080/EmployeeReimbursementSystem/";
		
}
