package chess.model.domain.move;

import chess.model.domain.board.ChessGame;
import chess.model.domain.state.MoveSquare;
import chess.model.domain.state.MoveState;
import util.NullChecker;

public class MoveStateAfter implements MoveStateStrategy {

    @Override
    public MoveState getMoveState(ChessGame chessGame, MoveSquare moveSquare) {
        NullChecker.validateNotNull(chessGame);
        if (chessGame.isKingCaptured()) {
            return MoveState.KING_CAPTURED;
        }
        if (chessGame.isNeedPromotion()) {
            return MoveState.SUCCESS_BUT_PAWN_PROMOTION;
        }
        return MoveState.SUCCESS;
    }
}
