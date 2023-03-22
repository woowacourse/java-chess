package chess.domain.piece;

import static chess.domain.square.Direction.DOWN;
import static chess.domain.square.Direction.DOWN_DOWN_LEFT;
import static chess.domain.square.Direction.DOWN_DOWN_RIGHT;
import static chess.domain.square.Direction.DOWN_LEFT;
import static chess.domain.square.Direction.DOWN_LEFT_LEFT;
import static chess.domain.square.Direction.DOWN_RIGHT;
import static chess.domain.square.Direction.DOWN_RIGHT_RIGHT;
import static chess.domain.square.Direction.LEFT;
import static chess.domain.square.Direction.RIGHT;
import static chess.domain.square.Direction.UP;
import static chess.domain.square.Direction.UP_LEFT;
import static chess.domain.square.Direction.UP_LEFT_LEFT;
import static chess.domain.square.Direction.UP_RIGHT;
import static chess.domain.square.Direction.UP_RIGHT_RIGHT;
import static chess.domain.square.Direction.UP_UP_LEFT;
import static chess.domain.square.Direction.UP_UP_RIGHT;

import java.util.Arrays;
import java.util.List;

import chess.domain.square.Direction;

public enum PieceDirection {

    WHITE_PAWN(UP, UP_LEFT, UP_RIGHT),
    BLACK_PAWN(DOWN, DOWN_LEFT, DOWN_RIGHT),
    KNIGHT(UP_UP_RIGHT, UP_RIGHT_RIGHT, DOWN_RIGHT_RIGHT, DOWN_DOWN_RIGHT,
            DOWN_DOWN_LEFT, DOWN_LEFT_LEFT, UP_LEFT_LEFT, UP_UP_LEFT),
    STRAIGHT(UP, RIGHT, DOWN, LEFT),
    DIAGONAL(UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT),
    KING_AND_QUEEN;

    private final List<Direction> pieceDirections;

    PieceDirection(final Direction... pieceDirections) {
        this.pieceDirections = Arrays.asList(pieceDirections);
    }

    private static int divideByAbs(final int number) {
        if (number == 0) {
            return number;
        }
        return number / Math.abs(number);
    }

    public boolean contains(final Direction direction) {
        return pieceDirections.contains(direction);
    }

    public Direction findDirection(final int fileDifference, final int rankDifference) {
        if (this == STRAIGHT) {
            return getAbsDirection(fileDifference, rankDifference);
        }
        if (this == DIAGONAL) {
            return getDiagonalDirection(fileDifference, rankDifference);
        }
        if (this == KING_AND_QUEEN) {
            return getKingAndQueenDirection(fileDifference, rankDifference);
        }
        return getDirection(fileDifference, rankDifference);
    }

    private Direction getAbsDirection(final int fileDifference, final int rankDifference) {
        final int fileDirection = divideByAbs(fileDifference);
        final int rankDirection = divideByAbs(rankDifference);
        return getDirection(fileDirection, rankDirection);
    }

    private Direction getDiagonalDirection(final int fileDifference, final int rankDifference) {
        if (Math.abs(fileDifference) == Math.abs(rankDifference)) {
            return getAbsDirection(fileDifference, rankDifference);
        }
        throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다.");
    }

    private Direction getKingAndQueenDirection(final int fileDifference, final int rankDifference) {
        try {
            return DIAGONAL.findDirection(fileDifference, rankDifference);
        } catch (IllegalArgumentException exception) {
            return STRAIGHT.findDirection(fileDifference, rankDifference);
        }
    }

    private Direction getDirection(final int fileDirection, final int rankDirection) {
        return pieceDirections.stream()
                .filter(direction ->
                        direction.getFileDirection() == fileDirection && direction.getRankDirection() == rankDirection)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다."));
    }
}
