package domain.commend;

import domain.pieces.Pieces;
import domain.team.Team;

public class State {
    private StateStrategy stateStrategy;
    private Team team;

    private State(Pieces pieces) {
        stateStrategy = new Waiting(pieces);
        team = Team.WHITE;
    }

    public static State of(Pieces pieces) {
        return new State(pieces);
    }

    public void start() {
        stateStrategy = stateStrategy.start();
    }

    public void end() {
        stateStrategy = stateStrategy.end();
    }

    public void move(String input) {
        stateStrategy = stateStrategy.move(team, input);
        changeTurn();
    }

    public double status() {
        stateStrategy = stateStrategy.status();
        if (team.equals(Team.BLACK)) {
            return ScoreType.calculateBlackScore(getPieces());
        }
        return ScoreType.calculateWhiteScore(getPieces());
    }

    private void changeTurn() {
        team = Team.opposite(team);
    }

    public boolean isFinished() {
        return stateStrategy.isFinished();
    }

    public Pieces getPieces() {
        return stateStrategy.pieces();
    }

    public Team getPresentTurn() {
        return team;
    }
}
