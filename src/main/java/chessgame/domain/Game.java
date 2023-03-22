package chessgame.domain;

import java.util.List;

import chessgame.controller.Command;
import chessgame.domain.point.Point;
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

    private void movePiece(List<Point> points) {
        board.move(points.get(0), points.get(1), state.team());
    }

    public Board board() {
        return board;
    }

    public boolean isNotEnd() {
        return state.isNotEnd();
    }
}
