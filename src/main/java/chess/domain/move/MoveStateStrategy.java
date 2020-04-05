package chess.domain.move;

import chess.domain.board.ChessGame;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;

public interface MoveStateStrategy {

    MoveState getMoveState(ChessGame chessGame, MoveSquare moveSquare);

}
