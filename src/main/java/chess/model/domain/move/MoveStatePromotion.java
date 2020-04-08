package chess.model.domain.move;

import chess.model.domain.board.ChessGame;
import chess.model.domain.state.MoveSquare;
import chess.model.domain.state.MoveState;
import util.NullChecker;

public class MoveStatePromotion implements MoveStateStrategy {

    @Override
    public MoveState getMoveState(ChessGame chessGame, MoveSquare moveSquare) {
        NullChecker.validateNotNull(chessGame);
        if (chessGame.isNeedPromotion()) {
            return MoveState.NEEDS_PROMOTION;
        }
        return MoveState.NO_PAWN_PROMOTION;
    }
}
