function initBoard() {
    const boardDiv = document.getElementById("board-div");

    const files = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
    const ranks = ['1', '2', '3', '4', '5', '6', '7', '8']

    const boardTable = document.createElement('table')
    ranks.reverse().forEach(initRank)
    boardDiv.appendChild(boardTable)

    function initRank(rank) {
        let tr = document.createElement('tr')
        tr.setAttribute('class', rank)

        files.forEach(function (file) {
            let td = document.createElement('td')
            setColorAttribute(file, rank, td)
            td.id = file + rank
            tr.append(td)
        })
        boardTable.appendChild(tr)
    }

    function setColorAttribute(file, rank, td) {
        const fileIndex = files.indexOf(file)
        const rankIndex = ranks.indexOf(rank)
        if ((fileIndex + rankIndex) % 2 === 0) {
            td.setAttribute('class', 'cell white-cell')
        }
        if (((fileIndex + rankIndex) % 2 === 1)) {
            td.setAttribute('class', 'cell black-cell')
        }
    }
}

function fillSquare(squareName, pieceType, pieceColor) {
    pieceColor = pieceColor.toLowerCase()
    pieceType = pieceType.toLowerCase()
    console.log(squareName + pieceType + pieceColor)
    if (pieceColor !== "nothing") {
        console.log(document.getElementById(squareName))
        document.getElementById(squareName).innerHTML = "<img class=piece-image src='/image/pieces/" + pieceColor + "/"
            + pieceColor + "-" + pieceType + ".svg" +"'/>"
    }
}
