package chess.domain.piece;

import chess.domain.exception.WrongDirectionException;
import chess.domain.square.Direction;

import java.util.Collections;
import java.util.List;

import static chess.domain.square.Direction.*;

public enum PieceDirection {

    WHITE_PAWN(List.of(UP, UP_LEFT, UP_RIGHT)),
    BLACK_PAWN(List.of(DOWN, DOWN_LEFT, DOWN_RIGHT)),
    KNIGHT(List.of(UP_UP_RIGHT, UP_RIGHT_RIGHT, DOWN_RIGHT_RIGHT, DOWN_DOWN_RIGHT,
            DOWN_DOWN_LEFT, DOWN_LEFT_LEFT, UP_LEFT_LEFT, UP_UP_LEFT)),
    STRAIGHT(List.of(UP, RIGHT, DOWN, LEFT)),
    DIAGONAL(List.of(UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT));

    private final List<Direction> pieceDirections;

    PieceDirection(final List<Direction> pieceDirections) {
        this.pieceDirections = pieceDirections;
    }

    public boolean contains(Direction direction) {
        return pieceDirections.contains(direction);
    }

    public static Direction findWhitePawnDirection(final int fileDifference, final int rankDifference) {
        return WHITE_PAWN.getDirection(fileDifference, rankDifference);
    }

    public static Direction findBlackPawnDirection(final int fileDifference, final int rankDifference) {
        return BLACK_PAWN.getDirection(fileDifference, rankDifference);
    }

    public static Direction findKnightDirection(final int fileDifference, final int rankDifference) {
        return KNIGHT.getDirection(fileDifference, rankDifference);
    }

    public static Direction findStraightDirection(final int fileDifference, final int rankDifference) {
        return STRAIGHT.getAbsDirection(fileDifference, rankDifference);
    }

    public static Direction findDiagonalDirection(final int fileDifference, final int rankDifference) {
        if (Math.abs(fileDifference) == Math.abs(rankDifference)) {
            return DIAGONAL.getAbsDirection(fileDifference, rankDifference);
        }
        throw new WrongDirectionException();
    }

    public static Direction findKingAndQueenDirection(final int fileDifference, final int rankDifference) {
        try {
            return findDiagonalDirection(fileDifference, rankDifference);
        } catch (WrongDirectionException exception) {
            return findStraightDirection(fileDifference, rankDifference);
        }
    }

    private Direction getDirection(final int fileDirection, final int rankDirection) {
        return pieceDirections.stream()
                .filter(direction -> direction.isSameDirection(fileDirection, rankDirection))
                .findAny()
                .orElseThrow(WrongDirectionException::new);
    }

    private Direction getAbsDirection(final int fileDifference, final int rankDifference) {
        final int fileDirection = divideByAbs(fileDifference);
        final int rankDirection = divideByAbs(rankDifference);
        return getDirection(fileDirection, rankDirection);
    }

    private static int divideByAbs(final int number) {
        if (number == 0) {
            return number;
        }
        return number / Math.abs(number);
    }
}
