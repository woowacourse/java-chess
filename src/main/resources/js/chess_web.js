window.onload = function () {
    var end = document.getElementById("end").innerText;
    var moveBtn = document.getElementById("moveBtn");
    var startBtn = document.getElementById("startBtn");

    if (end) {
        moveBtn.setAttribute("disabled", "disabled");
        alert(end);
    }

    if (window.location.pathname !== "/ready") {
        startBtn.value = "재시작";
    }
    if (window.location.pathname === "/ready") {
        moveBtn.setAttribute("disabled", "disabled");
    }
};
