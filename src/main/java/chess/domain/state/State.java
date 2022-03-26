package chess.domain.state;

import chess.domain.Board;
import chess.domain.Location;
import chess.domain.TeamScore;

public interface State {

    State start();

    State end();

    boolean isRunning();

    Board getBoard();

    State move(Location source, Location target);

    TeamScore getScore();
}
