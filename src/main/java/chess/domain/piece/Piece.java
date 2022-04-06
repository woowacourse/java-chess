package chess.domain.piece;

import chess.domain.piece.moving.MovingPattern;
import chess.domain.position.Position;

public class Piece {

    private final PieceType type;
    private final PieceColor color;
    private final MovingPattern movingPattern;

    public Piece(PieceType type, PieceColor color, MovingPattern movingPattern) {
        this.type = type;
        this.color = color;
        this.movingPattern = movingPattern;
    }

    public boolean isCorrectMovement(Position source, Position target, boolean hasEnemyPieceInTarget) {
        return movingPattern.canMove(source, target, hasEnemyPieceInTarget);
    }

    public double score() {
        return type.getScore();
    }

    public boolean canJumpOverPieces() {
        return type == PieceType.KNIGHT;
    }

    public boolean isKing() {
        return type == PieceType.KING;
    }

    public boolean isPawn() {
        return type == PieceType.PAWN;
    }

    public boolean isSameColor(PieceColor color) {
        return this.color == color;
    }

    public String getSignature() {
        return color.correctSignature(type.getSignature());
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }
}
