package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.position.Position;
import java.util.List;

public abstract class AbstractPiece {

    protected final PieceColor color;
    protected final List<Movement> movements;

    public AbstractPiece(PieceColor color) {
        this.color = color;
        this.movements = chooseMovements();
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    public boolean isSameColor(PieceColor color) {
        return this.color == color;
    }

    protected abstract List<Movement> chooseMovements();

    protected abstract String baseSignature();

    public abstract boolean isCorrectMovement(Position source, Position target, boolean hasTargetPiece);

    public abstract boolean canJumpOverPieces();

    public abstract double score();

    public abstract boolean isPawn();

    public abstract boolean isKing();
}
