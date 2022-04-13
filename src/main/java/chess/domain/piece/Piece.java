package chess.domain.piece;

import chess.domain.game.score.Score;
import chess.domain.position.Position;

public class Piece {
    private final PieceType pieceType;
    private final PieceColor pieceColor;

    public Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public boolean isAbleToMove(Position from, Position to) {
        return pieceType.isAbleToMove(from, to, pieceColor);
    }

    public boolean isAbleToAttack(Position from, Position to) {
        return pieceType.isAbleToAttack(from, to, pieceColor);
    }

    public boolean isAbleToJump() {
        return pieceType == PieceType.KNIGHT;
    }

    public boolean isKing() {
        return pieceType == PieceType.KING;
    }

    public boolean isPawn() {
        return pieceType == PieceType.PAWN;
    }

    public boolean isSameColorAs(PieceColor pieceColor) {
        return this.pieceColor == pieceColor;
    }

    public boolean isSameColorAs(Piece other) {
        return this.pieceColor == other.pieceColor;
    }

    // TODO: 게터 제거해야함
    // TODO: getScore 가 Piece 의 역할일까?
    public Score getScore() {
        return pieceType.getScore();
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                ", pieceColor=" + pieceColor +
                '}';
    }
}
