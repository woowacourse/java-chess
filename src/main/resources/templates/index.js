document.addEventListener("DOMContentLoaded", function () {

    $('.btn-makeRoom').click(async function () {
        $.ajax({
            type: "POST",
            url: "/room/create",
            data: {
                roomName: $('#makeRoomName').val(),
                userId: $('#creatorId').val()
            },
            success: function (data) {
                roomId = data
                window.location.href = "board.html?id=" + roomId
            },
            error: function (e) {
                console.log(e.message);
            }
        });
    });

    $('.btn-joinRoom').click(async function () {
        $.ajax({
            type: "POST",
            url: "/room/join",
            data: {
                roomName: $('#joinRoomName').val(),
                userId: $('#joinerId').val()
            },
            success: function (data) {
                roomId = data
                window.location.href = "board.html?id=" + roomId
            },
            error: function (e) {
                console.log(e.message);
            }
        });
    });
});
