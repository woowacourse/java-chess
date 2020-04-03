$(document).ready(function () {
    document.getElementsByClassName("command").forEach(command => {
        command.onclick = function () {
            command.setAttribute("value", "");
        }
    });
});