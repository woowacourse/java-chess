package chessgame.domain;

import java.util.List;

import chessgame.domain.point.Point;
import chessgame.domain.state.Button;

public class Game {
    private final Board board;
    private final Button button = new Button();
    private Team turn = Team.WHITE;

    public Game(Board board) {
        this.board = board;
    }

    public Board board() {
        return board;
    }

    public void setButton(Command command) {
        button.click(command);
    }

    public void movePiece(List<Point> points) {
        if (button.isStart()) {
            board.move(points.get(0), points.get(1), turn);
        }
        turn = turn.changeTurn();
    }

    public boolean isStart() {
        return button.isStart();
    }
}
