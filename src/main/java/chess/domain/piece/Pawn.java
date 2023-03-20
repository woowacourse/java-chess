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

    private List<Position> createMovablePositions(final Position source, final Position target) {
        final List<Position> result = new ArrayList<>();

        result.add(Position.of(source.getRow(), source.getColumn() + directionDecider()));
        if (source.isNotDistanceTwo(target)) {
            return result;
        }

        result.addAll(calculateDistanceTwoCases(source));
        return result;
    }

    private int directionDecider() {
        if (Color.WHITE == color) {
            return DIRECTION_UP;
        }
        return DIRECTION_DOWN;
    }

    private List<Position> calculateDistanceTwoCases(final Position source) {
        final List<Position> result = new ArrayList<>();
        if (isStartPosition(source)) {
            result.add(createPawnDoubleMove(source));
        }
        result.add(createPawnCaptureLeft(source));
        result.add(createPawnCaptureRight(source));
        return result;
    }

    private boolean isStartPosition(final Position source) {
        final int blackColorStartIndex = 1;
        final int whiteColorStartIndex = 6;
        return source.getColumn() == blackColorStartIndex || source.getColumn() == whiteColorStartIndex;
    }

    private Position createPawnCaptureRight(final Position source) {
        final int pawnDirection = directionDecider();
        return Position.of(source.getRow() + DIRECTION_RIGHT, source.getColumn() + pawnDirection);
    }

    private Position createPawnCaptureLeft(final Position source) {
        final int pawnDirection = directionDecider();
        return Position.of(source.getRow() + DIRECTION_LEFT, source.getColumn() + pawnDirection);
    }

    private Position createPawnDoubleMove(final Position source) {
        final int twoMove = 2;
        final int pawnDirection = directionDecider();
        return Position.of(source.getRow(), source.getColumn() + twoMove * pawnDirection);
    }
}
