$(document).ready( function() {
    $("#loginButton").on("click", function() {
        var usernameInput = $('#inputUser').val();
        var passwordInput = $('#inputPassword').val();

        checkIfPasswordIsCorrect(usernameInput, passwordInput)
    });

    $("#registerButton").on("click", function () {
        $('#createNewUserModal').modal('show')
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

    function create(checkIfUserExistBoolean, urlPOST, user) {
        if (checkIfUserExistBoolean){
            $('#errorCreatingNewUser').modal('show');
        } else{
            createNewUser(urlPOST, user);
            $('#successfulCreatingNewUser').modal('show');
            $('#createNewUserModal').modal('hide');
        }
    }

    function createNewUser(urlPOST, user) {
        $.ajax({
            url: urlPOST,
            type: 'POST',
            data: user,
            dataType: 'json',
            contentType: 'application/json'
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
                    $.each(item, function (index, item) {
                        var exist = pattern.test(index);
                        if (exist && item === username){
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
            if (this.readyState === 4) {
                var data = JSON.parse(this.responseText);
                for(var i = 0; i < data.length; i++) {
                    if(data[i].username === username && data[i].password === password) {
                        console.log("User found and acces granteed!")
                        document.cookie = data[i].id;
                        redirectToMovies();
                        foundUser = true;
                        break;
                    }
                }
                if (!foundUser) {
                    showWrongPasswordOrUserModal();
                }
            }
        });

        xhr.open("GET", "http://localhost:8080/user");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader("Cache-Control", "no-cache");
        xhr.setRequestHeader("Postman-Token", "f676027b-6dc2-1899-3135-1e81304d8cf3");

        xhr.send(data);
    }

    function redirectToMovies() {
        window.location.href = "http://localhost:8080/movies";
        return false;
    }

    function showWrongPasswordOrUserModal() {
        $('#wrongUsernameOrPassword').modal('show');
    }
});
