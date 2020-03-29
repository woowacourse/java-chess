package chess;

import chess.controller.ChessController;
import chess.domain.position.component.Row;

public class Application {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.play();
    }
}