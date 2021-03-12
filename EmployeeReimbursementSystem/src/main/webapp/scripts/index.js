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


			console.log("heading to employee Home");
            window.location = "http://localhost:8080/EmployeeReimbursementSystem/employeeHome.html";
			
			console.log("standard");	
			
			sessionStorage.setItem('userId', this.responseText)
			
			let SesInfo = sessionStorage.getItem('userId');
			
			console.log(`the current out is: ${SesInfo}`);

//			console.log(`the current user is: ${SesInfo[0]}`);

//			console.log(`the current err is: ${SesInfo[1]}`);
			
			let SesInfoOjb = JSON.parse(SesInfo);
			
			console.log(`the current user is: ${SesInfoOjb.e.username}`);

			console.log(`the current err is: ${SesInfoOjb.err.err}`);

			console.log(`the current list is: ${SesInfoOjb.list}`);

			console.log(`the current list[0] is: ${SesInfoOjb.list[0]}`);

			console.log(`the current list[1] is: ${SesInfoOjb.list[1]}`);

			console.log(`the current list[2] is: ${SesInfoOjb.list[2]}`);
			console.log(`the current list[2][0] is: ${SesInfoOjb.list[2][0]}`);
			console.log(`the current list[2][1] is: ${SesInfoOjb.list[2][1]}`);


			//let sesInfo=sessionStorage.setItem('userId', this.responseText)		
            //console.log(sessionStorage.setItem('userId', this.responseText));
			//console.log(JSON.parse(sesInfo));
			
			//console.log("loop");
			//for (let i=0; i<sesInfo.length(); i++){
			//	console.log(i);
			//	console.log(sesInfo[i]);
			//}
			
        }

        if (this.readyState === 4 && this.status === 204) { // 204 means NO CONTENT FOUND (but connection was made)

            console.log("failed to find user");

            let childDiv = document.getElementById('warningText');
            childDiv.textContent = "Failed to login!  Username of Password is incorrect"
        }
    }

	console.log("step 3");
    // 3. xhr.open("POST, "http:/localhost:8080/EmployeeDBServlet/url for the loginServlet")
    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/employeeLogin")

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

            window.location = "http://localhost:8080/EmployeeReimbursementSystem/managerHome.html";

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
    xhr.open("POST", "http://localhost:8080/EmployeeReimbursementSystem/managerLogin")

	console.log("step 4");
    // 4. xhr.send();
    xhr.send(JSON.stringify(loginTemplate))
	console.log("Done");
}
