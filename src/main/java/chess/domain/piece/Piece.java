package chess.domain.piece;

import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import java.util.List;

public class Piece {

    private final Color color;
    private final PieceType pieceType;

    public Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public boolean isMovable(final Square source, final Square destination, final BoardSnapShot boardSnapShot) {
        final List<Square> route = pieceType.findRoute(source, destination);
        if (isPawn()) {
            return boardSnapShot.canMovePawn(source, route);
        }
        return boardSnapShot.canMove(source, route);
    }

    public boolean isPawn() {
        return pieceType == PieceType.BLACK_PAWN || pieceType == PieceType.WHITE_PAWN;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean hasSameColor(final Piece piece) {
        return isSameColor(piece.color);
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public double getScore() {
        return pieceType.getScore();
    }
}
