function setBoard() {
    var map = new Map();
    map.set("k", "♔");
    map.set("K", "♚");
    map.set("q", "♕");
    map.set("Q", "♛");
    map.set("r", "♖");
    map.set("R", "♜");
    map.set("b", "♗");
    map.set("B", "♝");
    map.set("n", "♘");
    map.set("N", "♞");
    map.set("p", "♙");
    map.set("P", "♟");
    map.set("", "");

    document.querySelectorAll(".tile")
        .forEach(e = > {
        e.innerText = map.get(e.innerText);
})
    ;
}