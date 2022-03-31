package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.stream.IntStream;

public abstract class LinearMoveStrategy extends FirstRowMoveStrategy {

    private static final int FIRST_UNIT_FOR_ADD = 1;
    private static final int REVERSE_DIRECTION = -1;

    protected boolean isPieceExistAmongHorizon(final Board board,
                                               final Position smallerPosition,
                                               final Movement movement) {
        return IntStream.range(FIRST_UNIT_FOR_ADD, Math.abs(movement.getHorizon()))
                .mapToObj(moveUnit -> smallerPosition.move(moveUnit, Movement.NOT_MOVE))
                .anyMatch(position -> isPieceExist(board, position));
    }

    protected boolean isPieceExistAmongVertical(final Board board,
                                                final Position smallerPosition,
                                                final Movement movement) {
        return IntStream.range(FIRST_UNIT_FOR_ADD, Math.abs(movement.getVertical()))
                .mapToObj(moveUnit -> smallerPosition.move(Movement.NOT_MOVE, moveUnit * REVERSE_DIRECTION))
                .anyMatch(position -> isPieceExist(board, position));
    }

    protected boolean isPieceExistAmongPositiveDiagonal(final Board board,
                                                        final Position smallerPosition,
                                                        final Movement movement) {
        return IntStream.range(FIRST_UNIT_FOR_ADD, Math.abs(movement.getVertical()))
                .mapToObj(moveUnit -> smallerPosition.move(moveUnit * REVERSE_DIRECTION, moveUnit * REVERSE_DIRECTION))
                .anyMatch(position -> isPieceExist(board, position));
    }

    protected boolean isPieceExistAmongNegativeDiagonal(final Board board,
                                                        final Position smallerPosition,
                                                        final Movement movement) {
        return IntStream.range(FIRST_UNIT_FOR_ADD, Math.abs(movement.getVertical()))
                .mapToObj(moveUnit -> smallerPosition.move(moveUnit, moveUnit * REVERSE_DIRECTION))
                .anyMatch(position -> isPieceExist(board, position));
    }

    protected boolean isPieceExist(final Board board, final Position position) {
        return !board.getPiece(position).isBlank();
    }
}
