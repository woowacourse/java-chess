package chessgame.domain;

import java.util.List;

import chessgame.domain.point.Point;
import chessgame.domain.state.Ready;
import chessgame.domain.state.State;

public class Game {
    private final Board board;
    private State state = new Ready();

    public Game() {
        this.board = new Board();
    }

    public void changeState(Command command) {
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

    public Board board() {
        return board;
    }

    public void setState(Command command) {
        changeState(command);
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public void movePiece(List<Point> points) {
        board.move(points.get(0), points.get(1), state.team());
    }

}
