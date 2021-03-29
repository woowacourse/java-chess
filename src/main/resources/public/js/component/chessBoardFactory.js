import {store} from "../store.js";
import * as chessBoard from "./chessBoard.js";

export function createChessBoardAndPieces(gridDto, piecesResponseDto) {
    store.gridDto = gridDto;
    piecesResponseDto.sort((a, b) => {
        if (a.position[1] > b.position[1]) {
            return -1;
        } else if (a.position[1] < b.position[1]) {
            return 1;
        } else if (a.position[1] == b.position[1]) {
            if (a.position[0] > b.position[0]) {
                return 1;
            } else {
                return -1;
            }
        }
    });
    let pieces = []
    for (let i = 0; i < 8; i++) {
        pieces.push(piecesResponseDto.slice(i * 8, (i + 1) * 8));
    }
    store.pieces = pieces;
    const table = document.getElementById("chess-board");
    for (let i = 0; i < 8; i++) {
        const newTr = document.createElement("tr");
        for (let j = 0; j < 8; j++) {
            const newTd = document.createElement("td");

            const row = String(8 - i); // 열(12345678)
            const asciiNum = 'a'.charCodeAt(); // h의 아스키코드
            const column = String.fromCharCode(asciiNum + j);
            newTd.id = column + row;
            let pieceName = pieces[i][j].name;
            if (pieceName !== ".") {
                const piece = document.createElement("img");
                if (pieceName === pieceName.toUpperCase()) {
                    pieceName = "B" + pieceName.toUpperCase();
                } else {
                    pieceName = "W" + pieceName.toLowerCase();
                }
                piece.src = "images/" + pieceName + ".png";
                newTd.appendChild(piece);
            }
            if (i % 2 == j % 2) {
                newTd.className = "block1";
            } else {
                newTd.className = "block2";
            }
            newTr.appendChild(newTd);
        }
        table.appendChild(newTr);
    }
    chessBoard.addEvent();
}