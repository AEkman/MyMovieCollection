$(document).ready( function() {
    $("#loginButton").on("click", function() {
        var usernameInput = $('#inputUser').val();
        var passwordInput = $('#inputPassword').val();

        checkIfPasswordIsCorrect(usernameInput, passwordInput)
    });

    $("#registerButton").on("click", function () {
        $('#myModal').modal('show')
    });

    $("#createAccountButton").on("click", function () {
        var urlPOST = "http://localhost:8080/user/";
        var urlGET = "http://localhost:8080/user/";
        var username = $('#formControlInputName').val();
        var userPassword = $('#formControlInputPassword').val();
        var user = JSON.stringify({ "username": username, "password": userPassword });
        var checkIfUserExistBoolean = checkIfUserExist(urlGET, username);
        create(checkIfUserExistBoolean, urlPOST, user, username);
    });

    function create(checkIfUserExistBoolean, urlPOST, user, username) {
        if (checkIfUserExistBoolean){
            alert("Username: " + username + " already exist.");
        } else{
            createNewUser(urlPOST, user);
            $('#myModal').modal('hide');
        }
    }

    function createNewUser(urlPOST, user) {
        $.ajax({
            url: urlPOST,
            type: 'POST',
            data: user,
            dataType: 'json',
            contentType: 'application/json',
            success: alert("Success")
        });
    }

    function checkIfUserExist(urlGET, username) {
        var pattern = /username/;
        var returnBoolean = false;

        $.ajax({
            url: urlGET,
            type: 'GET',
            async: false,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                $.each(data, function (index, item) {
                    console.log(index + " : " + item);
                    $.each(item, function (index, item) {
                        console.log(index + " : " + item);
                        var exist = pattern.test(index);
                        if (exist && item === username){
                            console.log("checkIfUserExist found a user: " + item);
                            returnBoolean = true;
                        }
                    });
                })
            }
        });

        return returnBoolean;
    }

    function checkIfPasswordIsCorrect(username, password) {
        var foundUser = false;
        var data = JSON.stringify({
            "username": "user",
            "password": "user",
            "role": "USER"
        });

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
            if (this.status == 200 && this.readyState === 4) {
                var data = JSON.parse(this.responseText);
                for(var i = 0; i < data.length; i++) {
                    console.log(data[i].username + " : " + data[i].password);
                    if(data[i].username === username && data[i].password === password) {
                        document.cookie = data[i].id;
                        alert("Welcome user");
                        foundUser = true;
                        break;
                    }
                }
                if (!foundUser) {
                    alert("Wrong password or username");
                }
            }
        });

        xhr.open("GET", "http://localhost:8080/user", false);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader("Cache-Control", "no-cache");
        xhr.setRequestHeader("Postman-Token", "f676027b-6dc2-1899-3135-1e81304d8cf3");

        xhr.send(data);
    }
});
