$(document).ready(function () {
    document.getElementsByClassName("command").forEach(command => {
        command.onclick = function () {
            command.setAttribute("value", "");
        }
    });
});

if (document.getElementById("end").innerHTML === "true") {
    [...document.getElementsByClassName("command")].forEach(command => {
        command.setAttribute("disabled", true);
    });
    document.getElementById("input").setAttribute("disabled", true);
}