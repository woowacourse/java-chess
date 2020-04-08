package chess.model.domain.move;

import chess.model.domain.board.ChessGame;
import chess.model.domain.state.MoveSquare;
import chess.model.domain.state.MoveState;
import util.NullChecker;

public class MoveStateBefore implements MoveStateStrategy {

    @Override
    public MoveState getMoveState(ChessGame chessGame, MoveSquare moveSquare) {
        NullChecker.validateNotNull(chessGame, moveSquare);
        if (chessGame.isKingCaptured()) {
            return MoveState.KING_CAPTURED;
        }
        if (!chessGame.canMove(moveSquare)) {
            return getWhyCanMove(chessGame, moveSquare);
        }
        if (chessGame.isNeedPromotion()) {
            return MoveState.FAIL_MUST_PAWN_PROMOTION;
        }
        return MoveState.READY;
    }

    private MoveState getWhyCanMove(ChessGame chessGame, MoveSquare moveSquare) {
        if (chessGame.isNoPiece(moveSquare)) {
            return MoveState.FAIL_NO_PIECE;
        }
        if (chessGame.isNotMyTurn(moveSquare)) {
            return MoveState.FAIL_NOT_ORDER;
        }
        return MoveState.FAIL_CAN_NOT_MOVE;
    }
}
