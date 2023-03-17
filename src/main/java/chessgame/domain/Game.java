package chessgame.domain;

import java.util.List;

import chessgame.domain.point.Point;
import chessgame.domain.state.Power;

public class Game {
    private final Board board;
    private final Power power = new Power();
    private Team turn = Team.WHITE;

    public Game(Board board) {
        this.board = board;
    }

    public Board board() {
        return board;
    }

    public void setState(Command command) {
        power.click(command);
    }

    public void movePiece(List<Point> points) {
        boolean isMoved = false;
        if (power.isStart()) {
            isMoved = board.move(points.get(0), points.get(1), turn);
        }
        turn = turn.changeTurn();
    }

    public boolean isStart() {
        return power.isStart();
    }
}
