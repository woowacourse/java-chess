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

    public Game(Board board) {
        this.board = board;
        this.scores = new Scores();
        this.state = new Ready();
    }

    public Game(Board board, String state) {
        this.board = board;
        this.scores = new Scores();
        this.state = State.from(state);
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
            this.state = checkBoardIsOver(state);
        }
    }

    private State checkBoardIsOver(State state) {
        if (board().isOver()) {
            return new End(this.state.team());
        }
        return this.state = state;
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
        return !isNotEnd();
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

    public String state() {
        return state.name();
    }
}
