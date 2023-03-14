package chessgame.domain;

import java.util.Map;

import chessgame.domain.piece.Piece;
import chessgame.domain.point.Point;

public class Board {

    private final Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Board{" +
            "board=" + board +
            '}';
    }
}
