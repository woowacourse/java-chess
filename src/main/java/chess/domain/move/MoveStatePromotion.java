package chess.domain.move;

import chess.domain.board.ChessGame;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
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
