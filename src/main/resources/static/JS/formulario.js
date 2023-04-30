var check = function() {
    if (document.getElementById('password').value ==
      document.getElementById('password_repeat').value) {
      document.getElementById('message').style.color = 'green';
      document.getElementById('message').innerHTML = 'Las contrasenas son iguales';
      document.getElementById("btn_submit").disabled = false;

    } else {
      document.getElementById('message').style.color = 'red';
      document.getElementById('message').innerHTML = 'Las contrasenas NO son iguales';
      document.getElementById("btn_submit").disabled = true;

    }
  }



  var usernameInput = document.getElementById('username');
  usernameInput.addEventListener('keypress', function(event) {
    var regex = /^[a-zA-Z0-9]+$/;
    if (!regex.test(event.key)) {
        event.preventDefault();
      }
  });


  var locationInput = document.getElementById('localidad');
  locationInput.addEventListener('keypress', function(event) {
    var regex = /^[a-zA-Z]+$/;
    if (!regex.test(event.key)) {
        event.preventDefault();
      }
  });

  
var passwordInput = document.getElementById('password');
passwordInput.addEventListener('keypress', function(event) {
  var regex = /^[a-zA-Z0-9]+$/;
  if (!regex.test(event.key)) {
      event.preventDefault();
    }
});

var password_repeatInput = document.getElementById('password_repeat');
password_repeatInput.addEventListener('keypress', function(event) {
  var regex = /^[a-zA-Z0-9]+$/;
  if (!regex.test(event.key)) {
      event.preventDefault();
    }
});

  var btn_submit= document.getElementById("btn_submit");
btn_submit.addEventListener("click",createUser);

function createUser()
{
  //event.preventDefault(); // Evita que se recargue la página
    
  var username= document.getElementById("username").value;
  var password= document.getElementById("password").value;
  var password_repeat= document.getElementById("password_repeat").value;
  var localidad= document.getElementById("localidad").value;
  const body_html="username="+username+"&password="+password+"&password_repeat="+password_repeat+"&localidad="+localidad;

  var div=document.getElementById("div_ans");
  div.innerHTML="";

  fetch("http://localhost:8080/create_user",{method: "POST" , body:body_html})
  .then(function(res)
  {
    return res.text();
  })
  .then(function(data)
  {
    let h4=document.createElement("h4");
    h4.style.color="blue";
    h4.innerHTML=data;
    div.appendChild(h4);
  })
  .catch(function(error){
    let h4=document.createElement("h4");
    h4.style.color="blue";
    h4.innerHTML=error;
    div.appendChild(h4);
  })
  

};








  