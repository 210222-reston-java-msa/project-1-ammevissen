function employeeLogin(){
	console.log("send employee login");
	
	let eUsername=document.getElementById('employeeUsername').value;
	let ePassword=document.getElementById('employeePassword').value;
	
	console.log(`employee username ${eUsername}`);
	console.log(`employee password ${ePassword}`);


	let loginTemplate={
		username: eUsername,
		password: ePassword,
		role : 1
	}

	console.log("step 1");
	let xhr=new XMLHttpRequest();

	console.log("step 2");	
	xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

            sessionStorage.setItem('userId', this.responseText);

			console.log("heading to employee Home");
            window.location = "http://localhost:8080/EmployeeReimbursmentSystem/employeeHome.html";

            console.log(sessionStorage.getItem('userId'));
        }

        if (this.readyState === 4 && this.status === 204) { // 204 means NO CONTENT FOUND (but connection was made)

            console.log("failed to find user");

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login!  Username of Password is incorrect"
        }
    }

	console.log("step 3");
    // 3. xhr.open("POST, "http:/localhost:8080/EmployeeDBServlet/url for the loginServlet")
    xhr.open("POST", "http://localhost:8080/EmployeeReimbursmentSystem/employeeLogin")

	console.log("step 4");
    // 4. xhr.send();
    xhr.send(JSON.stringify(loginTemplate))
	console.log("Done");
}



function managerLogin(){
	console.log("send employee login");
	
	let mUsername=document.getElementById('managerUsername').value;
	let mPassword=document.getElementById('managerPassword').value;
	
	console.log(`manager username ${mUsername}`);
	console.log(`manager password ${mPassword}`);

	let loginTemplate={
		username: mUsername,
		password: mPassword,
		role : 2
	}

	console.log("step 1");
	let xhr=new XMLHttpRequest();

	console.log("step 2");	
	xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log("success");

            sessionStorage.setItem('currentUser', this.responseText)

            window.location = "http://localhost:8080/EmployeeReimbursmentSystem/managerHome.html";

            console.log(sessionStorage.getItem('currentUser'));
        }

        if (this.readyState === 4 && this.status === 204) { // 204 means NO CONTENT FOUND (but connection was made)

            console.log("failed to find user");

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login!  Username of Password is incorrect"
        }
    }

	console.log("step 3");
    // 3. xhr.open("POST, "http:/localhost:8080/EmployeeDBServlet/url for the loginServlet")
    xhr.open("POST", "http://localhost:8080/EmployeeReimbursmentSystem/managerLogin")

	console.log("step 4");
    // 4. xhr.send();
    xhr.send(JSON.stringify(loginTemplate))
	console.log("Done");
}
