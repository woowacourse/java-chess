async function fetchJson(url) {
    const response = await fetch(url);
    if (response.ok) {
        const json = await response.json();
        return json;
    }
    throw new Error("Server response status " + response.status);
}

const chessStart = () => {
    // board = Chessboard("myBoard", config)
    fetchJson("/api/board")
    .then(response => {
        if (response.queryStatus === "ok") return response.board;
    }).then(board => {
        board = Chessboard("myBoard", makeConfig(board));
    });
}

const onDrop = (source, target, piece) => {
    console.log('Source: ' + source)
    console.log('Target: ' + target)
    console.log('Piece: ' + piece)
};

const configTemplate = {
    draggable: true,
    position: "start",
    onDrop: onDrop
};

const makeConfig = position => {
    config = configTemplate;
    config.position = position;
    return config;
}

let board = Chessboard("myBoard");
