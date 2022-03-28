package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.TeamScore;

public interface State {

    State start();

    State end();

    boolean isRunning();

    Board getBoard();

    State move(Location source, Location target);

    TeamScore getScore();
}
