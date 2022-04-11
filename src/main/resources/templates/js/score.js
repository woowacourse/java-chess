function getScore() {
    $.ajax({
        url: "/status",
        type: "GET",
        success: function (data) {
            data = JSON.parse(data);
            document.getElementById("whiteScore").innerText = "White팀 점수: " + data["whiteScore"] + "점";
            document.getElementById("blackScore").innerText = "Black팀 점수: " + data["blackScore"] + "점";
        },
        error: function (data){
            alert(data);
        }
    })
}