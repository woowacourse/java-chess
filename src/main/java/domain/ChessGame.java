package domain;

import domain.piece.Piece;
import java.util.List;

public class ChessGame {
    private final ChessBoard chessBoard;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    public boolean move(Position from, Position to) {
        return chessBoard.move(from, to);
    }

    public List<Piece> getPiecesOnBoard() {
        return chessBoard.getPiecesOnBoard();
    }
}
