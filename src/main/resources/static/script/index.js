const spaces = document.querySelectorAll(".chess-table__space");
Array.from(spaces).map(space => {
    space.addEventListener('click', ({currentTarget: {id}}) => {
        console.log(id);
    });
});

fetch('http://localhost:8080/game/board-status', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
    }
})
    .then(response => response.json())
    .then(response => {
        response.forEach(({piece_url: pieceUrl, position, symbol}) => {
            const space = document.getElementById(position);
            space.innerHTML = `<img class="space__piece" src="${pieceUrl}" alt="${symbol}" />`;
        });
    });

