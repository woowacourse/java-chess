window.onload = function () {
    var end = document.getElementById("end").innerText;
    var moveBtn = document.getElementById("moveBtn");
    var startBtn = document.getElementById("startBtn");

    if (end) {
        moveBtn.setAttribute("disabled", "disabled");
        alert(end);
    }

    if (window.location.pathname !== "/enter") {
        startBtn.value = "재시작";
    }
    if (window.location.pathname === "/enter") {
        moveBtn.setAttribute("disabled", "disabled");
    }
};
