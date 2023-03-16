package chess.game;

import chess.board.ChessBoard;
import chess.board.Position;

public class ChessGame {

    private final ChessBoard chessBoard;

    public ChessGame() {
        this.chessBoard = ChessBoard.createBoard();
    }

    public void movePiece(final Position from, final Position to) {
        chessBoard.movePiece(from, to);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
