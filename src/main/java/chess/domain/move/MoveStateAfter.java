package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.state.MoveState;

public class MoveStateAfter implements MoveStateStrategy {

    @Override
    public MoveState getMoveState(ChessBoard chessBoard) {
        if (chessBoard.isKingCaptured()) {
            return MoveState.KING_CAPTURED;
        }
        if (chessBoard.isNeedPromotion()) {
            return MoveState.SUCCESS_BUT_PAWN_CHANGE;
        }
        return MoveState.SUCCESS;
    }
}
