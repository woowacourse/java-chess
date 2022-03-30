package chess;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.state.WhiteTurn;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard())));
        chessGame.consoleRun();
    }
}
