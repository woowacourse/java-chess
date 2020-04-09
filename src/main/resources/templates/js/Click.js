function onClick(value) {
    if (document.getElementsByClassName('sourcePosition').length) {
        return selectDestinationPiece(value);
    }
    sourcePosition(value);
}

function sourcePosition(value) {
    document.getElementById(value).classList.add('sourcePosition');
}

function resetSourcePosition() {
    document.getElementsByClassName('sourcePosition')[0].classList.remove('sourcePosition');
}

function movePiece(value) {
    function setPiece() {
        document.getElementById(value).innerHTML =
            document.getElementsByClassName('sourcePosition')[0].innerHTML;
        document.getElementsByClassName('sourcePosition')[0].innerHTML = "";
    }

    setPiece();
    resetSourcePosition();
}

function changeUrl(title, url, state) {
    if (typeof (history.pushState) != "undefined") {
        history.pushState(state, title, url);
    } else {
        location.href = url; //브라우저가 지원하지 않는 경우 페이지 이동 처리
    }
}

function selectDestinationPiece(value) {
    $.ajax({
        type: "post",
        url: "/move",
        data: {
            source: document.getElementsByClassName("sourcePosition")[0].id,
            target: document.getElementById(value).id
        },
        dataType: "text",
        success: function (data, status, jqXHR) {
            changeUrl("체스 게임","/chess",{'page_id': 1, 'user_id': 5});
            if (data) {
                console.log(jqXHR.responseText);
                alert(data);
            }
            movePiece(value);
        },
        error: function (jqXHR) {
            resetSourcePosition();
            console.log('error');
            alert(jqXHR.responseText);
        }
    });
}