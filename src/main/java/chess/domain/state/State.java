package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface State {

    State start();

    State move(Position source, Position target);

    State end();

    Board getBoard();

}
