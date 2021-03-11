function ManagerTask(){
    console.log("managerLogin pressed")

    let choice=document.getElementById("task").value;

    console.log(choice);
    if (choice==="approve reimbursments"){
        console.log("ap")
    }else if(choice==="view employees"){
        console.log("ve")
    }else if(choice==="view reimbursment requests"){
        console.log("vrr")
    }

}




var task = document.getElementById("submit");

task.addEventListener("click", ManagerTask);