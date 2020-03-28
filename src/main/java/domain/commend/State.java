package domain.commend;

import domain.pieces.Pieces;
import domain.team.Team;

public class State {
    private GameState gameState;
    private Team team;

    private State(Pieces pieces) {
        gameState = new Waiting(pieces);
        team = Team.WHITE;
    }

    public static State of(Pieces pieces) {
        return new State(pieces);
    }

    public void start() {
        gameState = gameState.start();
    }

    public void end() {
        gameState = gameState.end();
    }

    public void move(String input) {
        gameState = gameState.move(team, input);
        changeTurn();
    }

    private void changeTurn() {
        team = Team.opposite(team);
    }

    public boolean isFinished() {
        return gameState.isFinished();
    }

    public Pieces getPieces() {
        return gameState.pieces();
    }
}
