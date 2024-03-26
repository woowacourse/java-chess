package chess.domain.pieces.pawn;

import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.pieces.piece.Type;
import chess.domain.square.Movement;

public abstract class Pawn extends Piece {

    protected final int initialRank;
    protected final int defaultMove;
    protected final int initialMove;

    protected Pawn(final Color color, final int initialRank, final int defaultMove, final int initialMove) {
        super(color, Type.PAWN);
        this.initialRank = initialRank;
        this.defaultMove = defaultMove;
        this.initialMove = initialMove;
    }

    public static Pawn of(final Color color) {
        if (color.equals(Color.WHITE)) {
            return new WhitePawn();
        }
        return new BlackPawn();
    }

    @Override
    public boolean canMove(final Movement movement, final Piece target) {
        if (hasTargetPiece(target)) {
            return isAttack(movement);
        }
        return isMove(movement);
    }

    private boolean hasTargetPiece(final Piece target) {
        return target != null;
    }

    private boolean isAttack(final Movement movement) {
        return movement.isDiagonal() && movement.getRankDifference() == defaultMove;
    }

    private boolean isMove(final Movement movement) {
        if (isInitialSquare(movement)) {
            return isFirstMove(movement) || isDefaultMove(movement);
        }
        return isDefaultMove(movement);
    }

    private boolean isInitialSquare(final Movement movement) {
        return movement.getSourceRankIndex() == initialRank;
    }

    private boolean isFirstMove(final Movement movement) {
        return movement.isCross() && movement.getRankDifference() == initialMove;
    }

    private boolean isDefaultMove(final Movement movement) {
        return movement.isCross() && movement.getRankDifference() == defaultMove;
    }
}

