package chess.domain.state;

import chess.domain.Board;
import chess.domain.TeamScore;
import chess.domain.location.Location;
import chess.domain.piece.Team;

public interface State {

    State start();

    State end();

    boolean isRunning();

    Board getBoard();

    State move(Location source, Location target);

    TeamScore getScore();

    Team getTeam();
}
