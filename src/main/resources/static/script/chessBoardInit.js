document.body.onload = initBoard;

function initBoard() {
    const boardDiv = document.createElement("div");

    const files = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
    const ranks = ['1', '2', '3', '4', '5', '6', '7', '8']

    const boardTable = document.createElement('table')
    ranks.reverse().forEach(initRank)
    boardDiv.appendChild(boardTable)
    document.body.appendChild(boardDiv)

    function initRank(rank) {
        let tr = document.createElement('tr')
        tr.setAttribute('class', rank)

        files.forEach(function (file) {
            let td = document.createElement('td')
            setColorAttribute(file, rank, td)
            td.innerText = file + rank
            td.setAttribute('id', rank + file)
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
