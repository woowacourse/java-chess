package domain.commend;

import domain.pieces.Pieces;
import domain.team.Team;

public interface StateStrategy {

    StateStrategy start();

    StateStrategy end();

    StateStrategy move(Team team, String input);

    StateStrategy status();

    Pieces pieces();

    boolean isFinished();
}
