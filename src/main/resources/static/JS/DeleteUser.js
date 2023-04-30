var check = function() {
    if (document.getElementById('password').value ==
      document.getElementById('password_repeat').value) {
      document.getElementById('message').style.color = 'green';
      document.getElementById('message').innerHTML = 'Las contrasenas son iguales';
      document.getElementById("btn_detele_user").disabled = false;

    } else {
      document.getElementById('message').style.color = 'red';
      document.getElementById('message').innerHTML = 'Las contrasenas NO son iguales';
      document.getElementById("btn_detele_user").disabled = true;
    }
  }

var usernameInput = document.getElementById('username');
usernameInput.addEventListener('keypress', function(event) {
  var regex = /^[a-zA-Z0-9]+$/;
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






var btn_buscar_user = document.getElementById("btn_detele_user");
btn_buscar_user.addEventListener("click", deleteUser);



function deleteUser(event) {
    event.preventDefault(); // Evita que se recargue la página
    
    var username= document.getElementById("username").value;
    var password= document.getElementById("password").value;
    var password_repeat= document.getElementById("password_repeat").value;
    
    /*
    const body_html = {
        username: username, //clave: valor (referencia a document.getElemetn...)
        password: password,
        password_repeat: password_repeat,
        localidad: localidad
    };
    */

    const body_html="username="+username+"&password="+password+"&password_repeat="+password_repeat;

    var div = document.getElementById("div_ans");
    div.innerHTML = ""; //quitamos lo que hubiera antes, como la ultima busqueda

    fetch("http://localhost:8080/delete_user", {method:"DELETE", body: body_html})//JSON.stringify(body_html)})
    .then(function(res){
        return res.text();
    })
    .then(function(data)
    {
        var h4= document.createElement("h4");
        h4.innerHTML=data;
        div.appendChild(h4);
    })
    .catch(function(error)
    {
        var h4= document.createElement("h4");
        h4.innerHTML=error;
        div.appendChild(h4);
    });

}
