package domain.board;

import domain.Board;
import domain.piece.Empty;
import domain.piece.Piece;
import domain.point.File;
import domain.point.Point;
import domain.point.Rank;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Textures {
    public static Board makeBoard(Map<Point, Piece> pieces) {
        HashMap<Point, Piece> board = makeEmptyBoard();
        board.putAll(pieces);

        try {
            Constructor<Board> declaredConstructor = Board.class.getDeclaredConstructor(Map.class);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(board);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static HashMap<Point, Piece> makeEmptyBoard() {
        HashMap<Point, Piece> board = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(new Point(file, rank), new Empty());
            }
        }
        return board;
    }
}
