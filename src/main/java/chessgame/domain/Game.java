package chessgame.domain;

import chessgame.domain.point.Point;
import chessgame.domain.state.Button;
import chessgame.factory.ChessBoardFactory;

import java.util.List;

public class Game {
    private final Board board;
    private final Button button = new Button();
    private Team turn = Team.WHITE;

    public Game() {
        this.board = new Board(ChessBoardFactory.create());
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
