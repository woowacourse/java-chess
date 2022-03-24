package chess.domain.state;

import chess.domain.Board;
import chess.domain.Location;

public interface State {

    State start();

    State end();

    boolean isRunning();

    Board getBoard();

    State move(Location source, Location target);
}
