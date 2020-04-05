package chess.domain.move;

import chess.domain.board.Game;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;

public interface MoveStateStrategy {

    MoveState getMoveState(Game game, MoveSquare moveSquare);

}
