import {chessBoard} from './initialize.js';

let start = undefined;
let destination = undefined;

chessBoard.addEventListener('click', function (e) {
    if (e.target && e.target.nodeName === "IMG") {
        if (start === undefined) {
            start = e.target;
            start.style = "background-color: yellow";
        } else {
            destination = e.target;
            console.log(start.parentNode.id + "-->" + destination.parentNode.id);
            //서버요청!
            serverMoveRequest(start.parentNode.id, destination.parentNode.id);
            start.style = "background-color: none";
            start = undefined;
            destination = undefined;
        }
    }

    if (e.target && e.target.nodeName === "DIV") {
        destination = e.target;
        if (start !== undefined) {
            console.log(start.parentNode.id + "-->" + destination.id);
            //서버요청!
            serverMoveRequest(start.parentNode.id, destination.id);
            start.style = "background-color: none";
            start = undefined;
            destination = undefined;
        }
    }
})

function serverMoveRequest(startPoint, destPoint) {
    const moveRequest = {
        start: startPoint,
        destination: destPoint
    }

    const postOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(moveRequest)
    }

    fetch("/", postOption)
        .then(response => {
            if(!response.ok) {
                throw new Error(response.status);
            }
            return response.json();
        })
        .then(data => {
            movePieceUI(data.start, data.destination);
        })
        .catch(error => {
            console.log(error)
            alert("움직일 수 없는 경로입니다.");
        })
}

function movePieceUI(start, destination) {
    let startPosition = document.getElementById(start);
    let destinationPosition = document.getElementById(destination);
    let pieceImg = startPosition.firstChild.src;
    startPosition.firstChild.src = "";
    startPosition.firstChild.style = "display: none";
    destinationPosition.firstChild.src = pieceImg;
    destinationPosition.firstChild.style = "display: block";
}