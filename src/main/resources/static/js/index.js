$(document).ready( function() {
    $("#loginButton").on("click", function() {
        $.get("/user/", function (data) {
            alert(data);
        })
    });

    $("#registerButton").on("click", function () {
        $('#myModal').modal('show')
    });

    $("#createAccountButton").on("click", function () {
        var url = "http://localhost:8080/user/";

        var userName = $('#formControlInputName').val();
        var userPassword = $('#formControlInputPassword').val();

        var user = JSON.stringify({ "username": userName, "password": userPassword });

        $.ajax({
            url: url,
            type: 'POST',
            data: user,
            dataType: 'json',
            contentType: 'application/json'
        });
    });
});
