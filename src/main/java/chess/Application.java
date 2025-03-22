package chess;

import chess.view.View;

public class Application {
    public static void main(String[] args) {
        Chess chess = new Chess(new View());
        chess.play();
    }
}
