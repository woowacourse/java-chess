package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final int DIRECTION_DOWN = 1;
    private static final int DIRECTION_UP = -1;
    private static final int DIRECTION_RIGHT = 1;
    private static final int DIRECTION_LEFT = -1;

    private Pawn(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Pawn from(final Color color) {
        return new Pawn(PieceType.PAWN, color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        return createMovablePositions(source, target);
    }

    private int createPawnDirection() {
        int direction = DIRECTION_DOWN;
        if (Color.WHITE == color) {
            direction = DIRECTION_UP;
        }
        return direction;
    }

    private List<Position> createMovablePositions(final Position source, final Position target) {
        final List<Position> result = new ArrayList<>();

        addPawnDoubleMoveAndDiagonal(source, target, result);
        addPawnSingleMove(source, target, result);

        return result;
    }

    private void addPawnSingleMove(final Position source, final Position target, final List<Position> result) {
        if (!isTwoSquareMove(source, target)) {
            createFrontOneSquare(source, result);
        }
    }

    private void addPawnDoubleMoveAndDiagonal(final Position source, final Position target, final List<Position> result) {
        if (isTwoSquareMove(source, target)) {
            result.add(createLeftDiagonal(source));
            result.add(createRightDiagonal(source));
            if (isStartPosition(source)) {
                result.add(createFrontTwoSquares(source));
            }
        }
    }

    private void createFrontOneSquare(final Position source, final List<Position> result) {
        final int pawnDirection = createPawnDirection();
        result.add(Position.of(source.getRow(), source.getColumn() + pawnDirection));
    }

    private Position createFrontTwoSquares(final Position source) {
        final int pawnDirection = createPawnDirection();
        return Position.of(source.getRow(), source.getColumn() + 2 * pawnDirection);
    }

    private boolean isStartPosition(final Position source) {
        return source.getColumn() == 1 || source.getColumn() == 6;
    }

    private Position createRightDiagonal(final Position source) {
        final int pawnDirection = createPawnDirection();
        return Position.of(source.getRow() + DIRECTION_RIGHT, source.getColumn() + pawnDirection);
    }

    private Position createLeftDiagonal(final Position source) {
        final int pawnDirection = createPawnDirection();
        return Position.of(source.getRow() + DIRECTION_LEFT, source.getColumn() + pawnDirection);
    }

    private boolean isTwoSquareMove(final Position source, final Position target) {
        return 2 == Math.abs(source.getRow() - target.getRow()) + Math.abs(source.getColumn() - target.getColumn());
    }
}
