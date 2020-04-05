package chess.domain.move;

import chess.domain.board.Game;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import util.NullChecker;

public class MoveStatePromotion implements MoveStateStrategy {

    @Override
    public MoveState getMoveState(Game game, MoveSquare moveSquare) {
        NullChecker.validateNotNull(game);
        if (game.isNeedPromotion()) {
            return MoveState.NEEDS_PROMOTION;
        }
        return MoveState.NO_PAWN_PROMOTION;
    }
}
