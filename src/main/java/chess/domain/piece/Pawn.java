package chess.domain.piece;

import chess.domain.position.Move;
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
    public boolean isValidMove(Move move, Piece targetPiece) {
        if (isAttack(targetPiece)) {
            return canAttack(move);
        }
        if (!isTouched) {
            return canInitialMove(move);
        }
        return canMove(move);
    }

    private boolean isAttack(Piece targetPiece) {
        return targetPiece != null;
    }

    private boolean canAttack(Move move) {
        if (!move.isDiagonal()) {
            return false;
        }
        int deltaRank = move.getDeltaRank();
        if (isUpward()) {
            return deltaRank == 1;
        }
        return deltaRank == -1;
    }

    private boolean canInitialMove(Move move) {
        int deltaFile = move.getDeltaFile();
        int deltaRank = move.getDeltaRank();
        if (isUpward()) {
            return deltaFile == 0 && Set.of(1, 2).contains(deltaRank);
        }
        return deltaFile == 0 && Set.of(-1, -2).contains(deltaRank);
    }

    private boolean canMove(Move move) {
        int deltaFile = move.getDeltaFile();
        int deltaRank = move.getDeltaRank();
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
