package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.state.MoveState;

public class MoveStateChecker {

    private final ChessBoard chessBoard;

    public MoveStateChecker(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public MoveState check(MoveStateStrategy moveStateStrategy) {
        return moveStateStrategy.getMoveState(chessBoard);
    }

}
