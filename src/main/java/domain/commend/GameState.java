package domain.commend;

import domain.pieces.Pieces;
import domain.team.Team;

public interface GameState {
    GameState start();

    GameState end();

    GameState move(Team team, String input);

    Pieces pieces();

    boolean isFinished();
}
