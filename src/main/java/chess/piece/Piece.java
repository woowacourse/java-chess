package chess.piece;

import chess.position.UnitDirection;
import chess.score.PieceScore;
import chess.score.Score;
import java.util.Set;

public abstract class Piece {

    private final Color color;
    private final PieceScore pieceScore;
    private final Set<UnitDirection> unitDirections;

    protected Piece(Color color, PieceScore pieceScore, Set<UnitDirection> unitDirections) {
        this.color = color;
        this.pieceScore = pieceScore;
        this.unitDirections = unitDirections;
    }

    public boolean isMovable(UnitDirection direction, int step) {
        return unitDirections.contains(direction) &&
                isReachable(step);
    }

    protected abstract boolean isReachable(int step);

    public boolean canAttack(UnitDirection direction, int step) {
        return isMovable(direction, step);
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
