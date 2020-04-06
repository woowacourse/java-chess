package chess;

import chess.controller.ChessController;

public class Application {

    public static void main(String[] args) {
        try {
            ChessController.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
