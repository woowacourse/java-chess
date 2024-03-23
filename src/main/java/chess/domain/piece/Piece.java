package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;

public class Piece {

    private final PieceType pieceType;
    private final CampType campType;

    public Piece(PieceType pieceType, CampType campType) {
        this.pieceType = pieceType;
        this.campType = campType;
    }

    public boolean isBlack() {
        return campType.equals(CampType.BLACK);
    }

    public boolean isWhite() {
        return campType.equals(CampType.WHITE);
    }

    public boolean isSameType(String pieceViewName) {
        return pieceType.name().equals(pieceViewName);
    }

    public boolean isSameColor(Piece whitePiece) {
        return campType.equals(whitePiece.campType);
    }

    public boolean isNotEmpty() {
        return !pieceType.equals(PieceType.EMPTY);
    }

    public boolean canMove(Square source, Square destination, Board board) {
        return pieceType.canMove(source, destination, board);
    }
}
