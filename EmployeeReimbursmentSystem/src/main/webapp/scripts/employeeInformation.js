	let SesInfo = sessionStorage.getItem('userId');

	let firstName;
	let lastName;
	let username;
	let userID;
	let email;
	let role;

	if (SesInfo === null) {
		window.location = window.location = "http://localhost:8080/EmployeeReimbursmentSystem/index.html";;
	} else {
		
		let SesInfoOjb = JSON.parse(SesInfo); // parse the data that we see == to that attribute
		
		console.log(SesInfoOjb.userId);
		
		if (SesInfoOjb != null) {
			firstName=SesInfoOjb.firstName;
			lastName=SesInfoOjb.lastName;
			username=SesInfoOjb.username;
			userID=SesInfoOjb.userId;
			email=SesInfoOjb.email;
			role=SesInfoOjb.role;
		}
	}


document.getElementById("firstName").innerHTML=firstName
document.getElementById("lastName").innerHTML=lastName
document.getElementById("username").innerHTML=username
document.getElementById("userID").innerHTML=userID
document.getElementById("email").innerHTML=email
if (role=="1"){
    document.getElementById("role").innerHTML="  employee"

}else{
    document.getElementById("role").innerHTML="  manager"

}
