package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.state.MoveState;

public interface MoveStateStrategy {

    MoveState getMoveState(ChessBoard chessBoard);

}
