package chessgame.board;

import java.util.LinkedHashMap;
import java.util.Map;

import chessgame.point.File;
import chessgame.point.Point;
import chessgame.point.Rank;

public class Board {

    private final Map<Point, String> board;

    private Board(Map<Point, String> board) {
        this.board = board;
    }

    public static Board create() {
        Map<Point, String> initialBoard = new LinkedHashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                initialBoard.put(Point.of(rank, file), null);
            }
        }
        return new Board(initialBoard);
    }

    @Override
    public String toString() {
        return "Board{" +
            "board=" + board +
            '}';
    }
}
