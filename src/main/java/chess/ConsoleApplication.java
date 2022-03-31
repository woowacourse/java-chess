package chess;

import chess.controller.ChessGameController;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.state.WhiteTurn;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessGameController chessGame = new ChessGameController(new WhiteTurn(new Board(BoardInitializer.initBoard())));
        chessGame.run();
    }
}
