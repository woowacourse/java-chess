package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import util.NullChecker;

public class MoveStateBefore implements MoveStateStrategy {

    @Override
    public MoveState getMoveState(ChessBoard chessBoard, MoveSquare moveSquare) {
        NullChecker.validateNotNull(chessBoard, moveSquare);
        if (chessBoard.isKingCaptured()) {
            return MoveState.KING_CAPTURED;
        }
        if (!chessBoard.canMove(moveSquare)) {
            return getWhyCanMove(chessBoard, moveSquare);
        }
        if (chessBoard.isNeedPromotion()) {
            return MoveState.FAIL_MUST_PAWN_PROMOTION;
        }
        return MoveState.READY;
    }

    private MoveState getWhyCanMove(ChessBoard chessBoard, MoveSquare moveSquare) {
        if (chessBoard.isNoPiece(moveSquare)) {
            return MoveState.FAIL_NO_PIECE;
        }
        if (chessBoard.isNotMyTurn(moveSquare)) {
            return MoveState.FAIL_NOT_ORDER;
        }
        return MoveState.FAIL_CAN_NOT_MOVE;
    }
}
