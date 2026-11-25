let addExpenseForm = document.getElementById("AddExpenseForm");
addExpenseForm.addEventListener("submit",SendData);


function SendData(event){

    event.preventDefault();
    let title = document.getElementById("title").value;
    let amount = document.getElementById("amount").value;
    let category = document.getElementById("category").value;
    let description = document.getElementById("description").value;
    let date = document.getElementById("date").value;

    fetch("http://localhost:8080/ExpenseTracker/expense",{
        method:"POST",
         headers:{
             "Content-Type": "application/x-www-form-urlencoded"
        },
        body:new URLSearchParams({
            title:title,
            amount:amount,
            category:category,
            description:description,
            date:date,
            action:"addexpense"
        })
    })
	
    .then(reponse => reponse.text())
    .then(data => {
        data = data.trim();
        console.log(data + "asssssssss");				

        if (data === "success") {
			alert("Expenses added successfully");

		    window.location.href = "../index.html";
		}
		else {
		    alert("DataAdded was failed! Please try again.");
		  
		}
    })
      .catch(error =>{
        console.log(error);
    });

}