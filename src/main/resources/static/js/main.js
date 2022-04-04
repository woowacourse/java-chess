const boardUrl = '/board';
const moveUrl = `/move`;
let selected = '';
let from;

// 체스판 초기화
fetch(boardUrl)
    .then(res => res.json())
    .then(json => drawBoard(json));

// 클릭 이벤트 연동
document.querySelectorAll('.square')
    .forEach(square => square.addEventListener('click', (e) => {
        let selectedSquare = e.target;
        if (selected === '' && selectedSquare.dataset.piece === undefined) {
            return;
        }

        console.log(selectedSquare);

        if (selected === '') {
            selectedSquare.style.border = '3px solid pink';
            selected = selectedSquare.dataset.piece;
            from = selectedSquare.innerText;
            return;
        }

        if (selected !== '') {
            fetch(moveUrl, {
                method: 'POST',
                body: JSON.stringify({
                    piece: selected,
                    from: from,
                    to: selectedSquare.innerText
                })
            }).then(res => res.json())
                .then(moveResult => {
                    if (!moveResult.result) {
                        alert('이동에 실패했어요.. 🥲');
                        document.getElementById(moveResult.from).style.border = '';
                        return;
                    }
                    document.getElementById(moveResult.from).style.backgroundImage = '';
                    document.getElementById(moveResult.from).style.border = '';
                    document.getElementById(moveResult.to).style.backgroundImage = `url(/img/${moveResult.piece}.png)`;
                });

            selected = '';
            from = '';
        }
    }))


const drawBoard = (board) => {
    board.forEach(e => {
        const square = document.getElementById(e.position);
        square.dataset.piece = e.piece;
        square.style.backgroundImage = `url(/img/${e.piece}.png)`;
    });
}
