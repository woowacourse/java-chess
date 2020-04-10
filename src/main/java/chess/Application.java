package chess;

import controller.ChessGameController;

public class Application {
    public static void main(String[] args) {
        ChessGameController chessGameController = ChessGameController.getInstance();
        chessGameController.gameChess();
    }
}
