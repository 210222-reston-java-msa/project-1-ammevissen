/**
 * 
 */
function employeeLogin(){
    console.log("employeeLogin pressed")
}

function managerLogin(){
    console.log("managerLogin pressed")
}


var employeebtn = document.getElementById("employeebtn");

var managerbtn = document.getElementById("managerbtn");

employeebtn.addEventListener("click", employeeLogin);

managerbtn.addEventListener("click", managerLogin);