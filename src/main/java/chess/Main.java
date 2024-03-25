package chess;

import chess.controller.ChessController;

public class Main {
    public static void main(String[] args) {
        try {
            ChessController chessController = new ChessController();
            chessController.play();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
