package chess.domain.piece;

import chess.domain.grid.Column;
import chess.domain.grid.Lines;
import chess.domain.grid.Row;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Pawn extends Piece {
    private static final char NAME_WHEN_BLACK = 'P';
    private static final char NAME_WHEN_WHITE = 'p';
    private static final int STEP_RANGE = 1;
    private static final int TWO_STEP_RANGE = 2;
    private static final int SCORE = 1;
    private static final int TWO_STEP = 2;

    private boolean moved = false;

    public Pawn(final Color color, final Column column, final Row row) {
        super(color, column.getName(), row.getName());
    }

    public Pawn(final Color color, final char x, final int rowNumber) {
        super(color, x, rowNumber);
    }

    public Pawn(final Color color, final char x, final char y) {
        super(color, x, y);
    }

    public final boolean hasMoved() {
        return moved;
    }

    @Override
    void validateSteps(final Piece targetPiece, final Lines lines) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : directions()) {
            movablePositions.addAll(route(direction, stepRange(), lines));
        }
        for (Direction direction : twoStepDirections()) {
            movablePositions.addAll(route(direction, TWO_STEP_RANGE, lines));
        }
        movablePositions = movablePositions.stream()
                .distinct()
                .collect(Collectors.toList());
        validatePawnMove(targetPiece);
        targetPiece.validateTargetInMovablePositions(movablePositions);
        setMoved();
    }

    private void setMoved() {
        moved = true;
    }

    private List<Direction> twoStepDirections() {
        if (color() == Color.BLACK) {
            return Direction.blackPawnLinearDirection();
        }
        return Direction.whitePawnLinearDirection();
    }

    private void validatePawnMove(final Piece targetPiece) {
        validateTwoSteps(targetPiece);
        validateDiagonalMove(targetPiece);
        validateObstacleAhead(targetPiece);
    }

    private void validateTwoSteps(final Piece targetPiece) {
        if (hasMoved() && Math.abs(targetPiece.position().y() - position().y()) == TWO_STEP) {
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
        if (Math.abs(targetPiece.position().y() - position().y()) == 1 &&
                Math.abs(targetPiece.position().x() - position().x()) == 0 && !targetPiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 한칸 앞 말이 있으면 가지 못합니다.");
        }
    }

    @Override
    public final List<Direction> directions() {
        if (color() == Color.BLACK) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }

    @Override
    public final double score() {
        return SCORE;
    }

    @Override
    public int stepRange() {
        return STEP_RANGE;
    }

    @Override
    public char name() {
        if (color() == Color.BLACK) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}