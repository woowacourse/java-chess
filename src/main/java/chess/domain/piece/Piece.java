package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.strategy.MoveDirection;

import java.util.Objects;

public class Piece {

    private PieceKind pieceKind;
    private PieceColor pieceColor;

    public Piece(PieceKind pieceKind, PieceColor pieceColor) {
        this.pieceKind = pieceKind;
        this.pieceColor = pieceColor;
    }

    public boolean isSameColor(Piece piece) {
        return isSameColor(piece.pieceColor);
    }

    public boolean isSameColor(PieceColor anotherPieceColor) {
        return pieceColor.isSameColor(anotherPieceColor);
    }

    public boolean isMovingForward(Position source, Position target) {
        MoveDirection moveDirection = MoveDirection.getDirection(source, target);
        if (pieceColor.isSameColor(PieceColor.WHITE)) {
            return MoveDirection.isWhiteForward(moveDirection);
        }

        return MoveDirection.isBlackForward(moveDirection);
    }

    public boolean isPawn() {
        return pieceKind.isSameKind(PieceKind.PAWN);
    }

    public boolean isKing() {
        return pieceKind.isSameKind(PieceKind.KING);
    }

    public void movable(Position source, Position target) {
        pieceKind.movable(source, target);
    }

    public PieceKind kind() {
        return pieceKind;
    }

    public String symbol() {
        return pieceKind.getName(pieceColor);
    }

    public PieceColor color() {
        return this.pieceColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceKind == piece.pieceKind && pieceColor == piece.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceKind, pieceColor);
    }
}
