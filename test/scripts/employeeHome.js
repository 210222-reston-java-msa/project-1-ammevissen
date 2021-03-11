function task(){
    console.log("task choosen")
    let task=document.getElementById("task").value
    console.log(task)
}

var choice = document.getElementById("submit");

choice.addEventListener("click", task);