package chessgame.domain;

import chessgame.controller.Command;
import chessgame.domain.point.Points;
import chessgame.domain.state.Ready;
import chessgame.domain.state.State;

public class Game {
    private final Board board;
    private State state;

    public Game() {
        this.board = new Board();
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
        }
        this.state = state;
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    private void movePiece(Points points) {
        board.move(points, state.team());
    }

    public boolean isNotEnd() {
        return state.isNotEnd();
    }

    public Board board() {
        return board;
    }
}
