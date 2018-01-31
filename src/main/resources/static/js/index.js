$(document).ready( function() {
    $("#loginButton").on("click", function() {
        $("#mainContent").load('welcome.html');
    });
});

$("#registerButton").on("click", function () {
    $("#mainContent").load('registerUser.html')
});
