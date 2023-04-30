var usernameInput = document.getElementById('username');
usernameInput.addEventListener('keypress', function(event) {
  var regex = /^[a-zA-Z0-9]+$/;
  if (!regex.test(event.key)) {
      event.preventDefault();
    }
});





var btn_buscar_user = document.getElementById("btn_buscar_user");
btn_buscar_user.addEventListener("click", search_user);

function search_user() {
    var input = document.getElementById("username");

    var div = document.getElementById("div_ans");
    div.innerHTML = ""; //quitamos lo que hubiera antes, como la ultima busqueda

    fetch("http://localhost:8080/user_search/" + input.value, {method:"GET"})
        .then(function(res) {
            return res.json();
        })
        .then(function(data) {
            let h3 = document.createElement("h3");
            h3.innerHTML = "Usuarios que coinciden con su busqueda: ";
            div.appendChild(h3);

            //listamos usando un <ul>
            let ul = document.createElement("ul");
            for (let i = 0; i < data.length; i++) {
                let li = document.createElement("li");
                li.innerHTML = data[i];
                ul.appendChild(li);
            }
            div.appendChild(ul);
        })
        .catch(function(error) {
            var text = document.createElement("p");
            text.innerHTML = "No se ha encontrado ningun resultado para su busqueda";
            div.append(text);
        });
};
