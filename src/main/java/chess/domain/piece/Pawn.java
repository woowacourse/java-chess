package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public class Pawn extends Piece {
    private static final char NAME_WHEN_BLACK = 'P';
    private static final char NAME_WHEN_WHITE = 'p';
    private static final int STEP_RANGE = 1;
    private static final int TWO_STEP_RANGE = 2;
    private static final int SCORE = 1;

    private boolean moved = false;

    public Pawn(final Boolean isBlack, final char x, final char y) {
        super(isBlack, x, y);
    }

    public List<Direction> twoStepDirections() {
        if (isBlack()) {
            return Direction.blackPawnLinearDirection();
        }
        return Direction.whitePawnLinearDirection();
    }

    public int twoStepRange() {
        return TWO_STEP_RANGE;
    }

    public void moved() {
        moved = true;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void validatePawnMove(final Piece targetPiece) {
        validateTwoSteps(targetPiece);
        validateDiagonalMove(targetPiece);
        validateObstacleAhead(targetPiece);
    }

    private void validateTwoSteps(final Piece targetPiece) {
        if (hasMoved() && Math.abs(targetPiece.position().y() - position().y()) == 2) {
            throw new IllegalArgumentException("폰은 초기 자리에서만 두칸 이동 가능합니다.");
        }
    }

    private void validateDiagonalMove(final Piece targetPiece) {
        if (Math.abs(targetPiece.position().y() - position().y()) == 1 &&
                Math.abs(targetPiece.position().x() - position().x()) == 1 && targetPiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 상대 말을 먹을 때만 대각선으로 이동이 가능합니다.");
        }
    }

    private void validateObstacleAhead(final Piece targetPiece) {
        if (Math.abs(targetPiece.position().y() - position().y()) == 1 && Math.abs(targetPiece.position().x() - position().x()) == 0 && !targetPiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 한칸 앞 말이 있으면 가지 못합니다.");
        }
    }

    @Override
    public List<Direction> directions() {
        if (isBlack()) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public int stepRange() {
        return STEP_RANGE;
    }

    @Override
    public char name() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}