document.addEventListener("DOMContentLoaded", SendData);

function SendData(event) {
    event.preventDefault();

      fetch("http://localhost:8080/ExpenseTracker/expense", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            action: "viewhistory"
        })
    })
        .then(response => response.json())
        .then(data => {
			console.log(data[1]);
			let historyList = document.getElementById("historyList");

			data.forEach(history => {

			    console.log("Inside forEach");

			    historyList.innerHTML += `
			        <div class="history-card">
			            <h3>${history.title}</h3>
			            <p>Amount: ${history.amount}</p>
			            <p>CreatedDate: ${history.createdDate}</p>
			            <p>Category: ${history.category}</p>
			            <p>Date: ${history.date}</p>
			            <p>Description: ${history.description}</p>
			        </div>
			    `;

			    console.log(historyList.innerHTML);

			});
        })
        .catch(error => {
            console.log(error);
        });
}