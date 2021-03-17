package chess.board;


import chess.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Point, State> squares = new HashMap<>();


    public Board() {
        Point.getAllPoints()
            .forEach(point -> squares.put(point, State.of(Piece.EMPTY, Team.NONE)));
    }

    public void putSymmetrically(Piece piece, Point point) {
        squares.put(point, State.of(piece, Team.WHITE));
        squares.put(point.opposite(), State.of(piece, Team.BLACK));
    }

    public State getState(Point point) {
        return squares.get(point);
    }

    public boolean canMove(Point source, Point destination) {
        if (!squares.get(source).canMove(source, destination)) {
            throw new IllegalArgumentException("해당 위치로는 이동할 수 없는 말입니다.");
        }

        if (squares.get(destination).isSameTeam(squares.get(source))) {
            throw new IllegalArgumentException("아군 말이 있는 곳에는 이동할 수 없습니다");
        }

        return true;
    }

    public void move(Point source, Point destination) {
        squares.put(destination, squares.get(source));
        squares.put(source, State.of(Piece.EMPTY, Team.NONE));
    }
}
