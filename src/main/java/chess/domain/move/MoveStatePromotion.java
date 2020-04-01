package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.state.MoveState;

public class MoveStatePromotion implements MoveStateStrategy {

    @Override
    public MoveState getMoveState(ChessBoard chessBoard) {
        if (chessBoard.isNeedPromotion()) {
            return MoveState.SUCCESS;
        }
        return MoveState.SUCCESS_BUT_PAWN_CHANGE;
    }
}
