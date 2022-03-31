package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    protected final Color color;
    protected final List<Movement> movements;

    public Piece(Color color) {
        this.color = color;
        this.movements = chooseMovements();
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    public boolean isEnemyColorPiece(Piece piece) {
        return this.color.enemyColor() == piece.color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public Piece displaced() {
        return this;
    }

    protected abstract List<Movement> chooseMovements();

    protected abstract String baseSignature();

    public abstract boolean isCorrectMovement(Position source, Position target, Piece targetPiece);

    public abstract boolean canJumpOverPieces();

    public abstract double score();

    public abstract boolean isBlank();

    public abstract boolean isPawn();

    public abstract boolean isKing();
}
