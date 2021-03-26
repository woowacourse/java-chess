package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;

public class PawnMoveStrategy extends BasicMoveStrategy {

    private static final int PAWN_INITIAL_DISTANCE_LIMIT = 2;
    private static final int PAWN_GENERAL_DISTANCE_LIMIT = 1;
    private static final int PAWN_WHITE_INITIAL_POSITION = 2;
    private static final int PAWN_BLACK_INITIAL_POSITION = 7;

    @Override
    void checkValidMove(final Position source, final Position target) {
        checkValidDistance(source, target);
    }

    private void checkValidDistance(final Position source, final Position target) {
        if (source.sameYPosition(PAWN_WHITE_INITIAL_POSITION) ||
            source.sameYPosition(PAWN_BLACK_INITIAL_POSITION)) {
            checkInitialMoveDistance(source, target);
            return;
        }

        checkGeneralMoveDistance(source, target);
    }

    private void checkInitialMoveDistance(final Position source, final Position target) {
        if (Math.abs(source.computeVerticalDistance(target)) > PAWN_INITIAL_DISTANCE_LIMIT) {
            throw new InvalidMoveException(Piece.OVER_DISTANCE_MESSAGE);
        }
    }

    private void checkGeneralMoveDistance(final Position source, final Position target) {
        if (Math.abs(source.computeHorizontalDistance(target)) > PAWN_GENERAL_DISTANCE_LIMIT ||
            Math.abs(source.computeVerticalDistance(target)) > PAWN_GENERAL_DISTANCE_LIMIT) {
            throw new InvalidMoveException(Piece.OVER_DISTANCE_MESSAGE);
        }
    }
}
