package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;

public interface State {

    State start();

    State move(Coordinate from, Coordinate to);

    State end();

    Board getBoard();

    boolean isFinished();
}
