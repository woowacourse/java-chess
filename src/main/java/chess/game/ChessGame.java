package chess.game;

import chess.board.ChessBoard;

public class ChessGame {

    private ChessBoard chessBoard;

    public ChessGame() {
        this.chessBoard = ChessBoard.createBoard();
    }

    public ChessBoard receiveCommand(final Command command) {
        if (command == Command.START) {
            return this.chessBoard;
        }

        return null;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
