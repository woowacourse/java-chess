package chessgame.domain;

import chessgame.controller.Command;
import chessgame.domain.point.Points;
import chessgame.domain.state.End;
import chessgame.domain.state.Ready;
import chessgame.domain.state.State;

public class Game {
    private final Board board;
    private final Scores scores;
    private State state;

    public Game() {
        this.board = new Board();
        this.scores = new Scores();
        this.state = new Ready();
    }

    public void setFrom(Command command) {
        state.changeState(this, command);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setState(Command command, State state) {
        if (isRunning()) {
            movePiece(command.points());
            if (board().isOver()) {
                this.state = new End(this.state.team());
            } else {
                this.state = state;
            }
        }
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    private void movePiece(Points points) {
        board.move(points, state.team());
    }

    public void calculateScore() {
        for (Team team : Team.values()) {
            scores.set(team, board.calculateScore(team));
        }
    }

    public boolean isNotEnd() {
        return state.isNotEnd();
    }

    public boolean isEnd() {
        return !isNotEnd() && state.team() != null;
    }

    public Board board() {
        return board;
    }

    public Scores score() {
        return scores;
    }

    public Team winner() {
        return state.team();
    }
}
