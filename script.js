const API_URL = "http://108.131.153.250:8080";

let userId = 1;

// LOGIN
async function login(){

const email = document.getElementById("email").value;
const password = document.getElementById("password").value;

try{

const response = await fetch(`${API_URL}/login?loginEmail=${email}&loginPassword=${password}`);

const data = await response.json();

console.log(data);


userId = data.userId || 1;

localStorage.setItem("userId", userId);

window.location.href = "dashboard.html";

}catch(error){

document.getElementById("error").innerText = "Login failed";

}

}


// CREATE EVENT
async function addEvent(){

const dateTime = document.getElementById("dateTime").value;
const latitude = document.getElementById("latitude").value;
const longitude = document.getElementById("longitude").value;

const userId = localStorage.getItem("userId");

await fetch(`${API_URL}/schedule`, {

method:"POST",

headers:{
"Content-Type":"application/json"
},

body:JSON.stringify({

userId: parseInt(userId),
dateTime: dateTime,
latitude: parseFloat(latitude),
longitude: parseFloat(longitude)

})

});

alert("Event added");

}


// GET EVENTS
async function getEvents(){

const userId = localStorage.getItem("userId");
const response = await fetch(`${API_URL}/schedule?userId=${userId}`);
const events = await response.json();
const container = document.getElementById("events");

container.innerHTML = "";

events.forEach(event => {

const div = document.createElement("div");

div.className = "event";

//inputs 
div.innerHTML = `
<p><b>Date:</b> ${event.dateTime}</p>
<p><b>Latitude:</b> ${event.latitude}</p>
<p><b>Longitude:</b> ${event.longitude}</p>
`;

container.appendChild(div);

});

}