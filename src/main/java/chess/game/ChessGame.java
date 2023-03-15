package chess.game;

import chess.board.ChessBoard;

public class ChessGame {

    private ChessBoard chessBoard;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public ChessBoard receiveCommand(final Command command) {
        if (command == Command.START) {
            return this.chessBoard;
        }

        return null;
    }
}
