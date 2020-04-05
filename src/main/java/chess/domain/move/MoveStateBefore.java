package chess.domain.move;

import chess.domain.board.Game;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import util.NullChecker;

public class MoveStateBefore implements MoveStateStrategy {

    @Override
    public MoveState getMoveState(Game game, MoveSquare moveSquare) {
        NullChecker.validateNotNull(game, moveSquare);
        if (game.isKingCaptured()) {
            return MoveState.KING_CAPTURED;
        }
        if (!game.canMove(moveSquare)) {
            return getWhyCanMove(game, moveSquare);
        }
        if (game.isNeedPromotion()) {
            return MoveState.FAIL_MUST_PAWN_PROMOTION;
        }
        return MoveState.READY;
    }

    private MoveState getWhyCanMove(Game game, MoveSquare moveSquare) {
        if (game.isNoPiece(moveSquare)) {
            return MoveState.FAIL_NO_PIECE;
        }
        if (game.isNotMyTurn(moveSquare)) {
            return MoveState.FAIL_NOT_ORDER;
        }
        return MoveState.FAIL_CAN_NOT_MOVE;
    }
}
