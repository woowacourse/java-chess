package chess.domain.piece;

import chess.domain.piece.exception.WrongDirectionException;
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
    DIAGONAL(List.of(UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT)),
    KING_AND_QUEEN(Collections.emptyList());

    private final List<Direction> pieceDirections;

    PieceDirection(final List<Direction> pieceDirections) {
        this.pieceDirections = pieceDirections;
    }

    public boolean contains(Direction direction) {
        return pieceDirections.contains(direction);
    }

    // TODO: 코드 개선 필요
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

    private Direction getKingAndQueenDirection(final int fileDifference, final int rankDifference) {
        try {
            return DIAGONAL.findDirection(fileDifference, rankDifference);
        } catch (WrongDirectionException exception) {
            return STRAIGHT.findDirection(fileDifference, rankDifference);
        }
    }

    private Direction getDiagonalDirection(final int fileDifference, final int rankDifference) {
        if (Math.abs(fileDifference) == Math.abs(rankDifference)) {
            return getAbsDirection(fileDifference, rankDifference);
        }
        throw new WrongDirectionException();
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

    private Direction getDirection(final int fileDirection, final int rankDirection) {
        return pieceDirections.stream()
                .filter(direction ->
                        direction.getFileDirection() == fileDirection && direction.getRankDirection() == rankDirection)
                .findAny()
                .orElseThrow(WrongDirectionException::new);
    }
}
