package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Set;

public class Pawn extends Piece {

    private final boolean isTouched;

    public Pawn(Color color) {
        super(color);
        this.isTouched = false;
    }

    private Pawn(Color color, boolean isTouched) {
        super(color);
        this.isTouched = isTouched;
    }

    @Override
    public boolean isValidMove(Position source, Position target, Piece targetPiece) {
        if (isAttack(targetPiece)) {
            return canAttack(source, target);
        }
        int deltaFile = source.getDeltaFile(target);
        int deltaRank = source.getDeltaRank(target);
        if (!isTouched) {
            return canInitialMove(deltaFile, deltaRank);
        }
        return canMove(deltaFile, deltaRank);
    }

    private boolean isAttack(Piece targetPiece) {
        return targetPiece != null;
    }

    private boolean canAttack(Position source, Position target) {
        if (!source.isDiagonal(target)) {
            return false;
        }
        int deltaRank = source.getDeltaRank(target);
        if (isUpward()) {
            return deltaRank == 1;
        }
        return deltaRank == -1;
    }

    private boolean canInitialMove(int deltaFile, int deltaRank) {
        if (isUpward()) {
            return deltaFile == 0 && Set.of(1, 2).contains(deltaRank);
        }
        return deltaFile == 0 && Set.of(-1, -2).contains(deltaRank);
    }

    private boolean canMove(int deltaFile, int deltaRank) {
        if (isUpward()) {
            return deltaFile == 0 && deltaRank == 1;
        }
        return deltaFile == 0 && deltaRank == -1;
    }

    @Override
    public Piece touch() {
        if (!isTouched) {
            return createTouchedPawn();
        }
        return this;
    }

    private Pawn createTouchedPawn() {
        return new Pawn(color, true);
    }

    private boolean isUpward() {
        return color == Color.WHITE;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
