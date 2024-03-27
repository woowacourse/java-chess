package chess.piece;

import chess.position.UnitMovement;
import chess.score.PieceScore;
import chess.score.Score;
import java.util.Set;

public abstract class Piece {

    private final Color color;
    private final PieceScore pieceScore;
    private final Set<UnitMovement> unitMovements;

    protected Piece(Color color, PieceScore pieceScore, Set<UnitMovement> unitMovements) {
        this.color = color;
        this.pieceScore = pieceScore;
        this.unitMovements = unitMovements;
    }

    public boolean isMovable(UnitMovement movement, int step) {
        return unitMovements.contains(movement) &&
                isReachable(step);
    }

    protected abstract boolean isReachable(int step);

    public boolean canAttack(UnitMovement movement, int step) {
        return isMovable(movement, step);
    }

    public boolean isInitPawn() {
        return false;
    }

    public boolean hasSameColorWith(Piece piece) {
        return color == piece.color;
    }

    public boolean hasDifferentColorWith(Piece piece) {
        return !hasSameColorWith(piece);
    }

    public boolean hasColorOf(Color color) {
        return this.color == color;
    }

    public boolean hasOpponentColorOf(Color color) {
        return this.color != color;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isNotPawn() {
        return !isPawn();
    }

    public boolean isKing() {
        return false;
    }

    public Score getScore() {
        return pieceScore.asScore();
    }

    public Color getColor() {
        return color;
    }
}
