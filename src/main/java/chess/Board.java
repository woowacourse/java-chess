package chess;


import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Point, State> squares = new HashMap<>();

    public Board() {
        Point.getAllPoints()
            .forEach(point -> squares.put(point, new State(Piece.EMPTY, Team.NONE)));
    }

    public void putSymmetrically(Piece piece, Point point) {
        squares.put(point, new State(piece, Team.WHITE));
        squares.put(point.opposite(), new State(piece, Team.BLACK));
    }

    public State getState(String point) {
        return squares.get(Point.of(point));
    }
}
