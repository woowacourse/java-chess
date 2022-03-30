package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.stream.IntStream;

public abstract class LinearMoveStrategy extends FirstRowMoveStrategy {

    private static final int ADD_START_UNIT = 1;
    private static final int REVERSE_DIRECTION = -1;

    protected boolean isPieceExistWhenHorizon(final Board board,
                                              final Position smallerPosition,
                                              final Distance distance) {
        return IntStream.range(ADD_START_UNIT, Math.abs(distance.getHorizon()))
                .mapToObj(moveUnit -> smallerPosition.move(moveUnit, NOT_MOVE))
                .anyMatch(board::isPieceExist);
    }

    protected boolean isPieceExistWhenVertical(final Board board,
                                               final Position smallerPosition,
                                               final Distance distance) {
        return IntStream.range(ADD_START_UNIT, Math.abs(distance.getVertical()))
                .mapToObj(moveUnit -> smallerPosition.move(NOT_MOVE, moveUnit * REVERSE_DIRECTION))
                .anyMatch(board::isPieceExist);
    }

    protected boolean isPieceExistWhenPositiveDiagonal(final Board board,
                                                       final Position smallerPosition,
                                                       final Distance distance) {
        return IntStream.range(ADD_START_UNIT, Math.abs(distance.getVertical()))
                .mapToObj(moveUnit -> smallerPosition.move(moveUnit * REVERSE_DIRECTION, moveUnit * REVERSE_DIRECTION))
                .anyMatch(board::isPieceExist);
    }

    protected boolean isPieceExistWhenNegativeDiagonal(final Board board,
                                                       final Position smallerPosition,
                                                       final Distance distance) {
        return IntStream.range(ADD_START_UNIT, Math.abs(distance.getVertical()))
                .mapToObj(moveUnit -> smallerPosition.move(moveUnit, moveUnit * REVERSE_DIRECTION))
                .anyMatch(board::isPieceExist);
    }
}
