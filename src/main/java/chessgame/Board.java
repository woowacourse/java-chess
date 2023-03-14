package chessgame;

import java.util.Map;

import chessgame.point.Point;

public class Board {

    private final Map<Point, Pieces> board;

    public Board(Map<Point, Pieces> board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Board{" +
            "board=" + board +
            '}';
    }
}
