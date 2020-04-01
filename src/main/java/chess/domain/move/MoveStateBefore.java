package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;

public class MoveStateBefore implements MoveStateStrategy {

    public final MoveSquare moveSquare;

    public MoveStateBefore(MoveSquare moveSquare) {
        this.moveSquare = moveSquare;
    }

    @Override
    public MoveState getMoveState(ChessBoard chessBoard) {
        if (chessBoard.isKingCaptured()) {
            return MoveState.KING_CAPTURED;
        }
        if (!chessBoard.canMove(moveSquare)) {
            return getWhyCanMove(chessBoard);
        }
        if (chessBoard.isNeedPromotion()) {
            return MoveState.FAIL_MUST_PAWN_CHANGE;
        }
        return MoveState.READY;
    }

    private MoveState getWhyCanMove(ChessBoard chessBoard) {
        if (chessBoard.isNoPiece(moveSquare)) {
            return MoveState.FAIL_NO_PIECE;
        }
        if (chessBoard.isNotMyTurn(moveSquare)) {
            return MoveState.FAIL_NOT_ORDER;
        }
        return MoveState.FAIL_CAN_NOT_MOVE;
    }
}
