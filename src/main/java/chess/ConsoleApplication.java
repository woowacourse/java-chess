package chess;

import chess.controller.ChessGameController;
import chess.domain.state.BoardInitializer;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessGameController chessGame = new ChessGameController(BoardInitializer.initBoard());
        chessGame.run();
    }
}
