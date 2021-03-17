package chess.board;


import chess.piece.Direction;
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
        State sourceState = squares.get(source);
        State destinationState = squares.get(destination);
        Direction direction = sourceState.findMovableDirection(source, destination);
        if (direction == null) {
            return false;
        }

        return sourceState.isNotEmpty()
            && isNotSameTeam(sourceState, destinationState)
            && isNotBlockedToGo(source, destination, direction);
    }

    private boolean isNotSameTeam(State sourceState, State destinationState) {
        return destinationState.isNotSameTeam(sourceState);
    }

    private boolean isNotBlockedToGo(Point source, Point destination, Direction direction) {
        State sourceState = squares.get(source);

        int moveCount = 1;
        boolean unblocked = true;
        for (Point now = source.move(direction); isOnGoing(destination, now) && unblocked;
            now = now.move(direction)) {
            unblocked =
                underDirectionLength(sourceState, moveCount) && squares.get(now).isEmpty();

            moveCount++;
        }
        return unblocked;
    }

    private boolean isOnGoing(Point destination, Point now) {
        return !now.equals(destination);
    }

    private boolean underDirectionLength(State sourceState, int moveCount) {
        return moveCount <= sourceState.getDirectionLength();
    }

    public void move(Point source, Point destination) {
        squares.put(destination, squares.get(source));
        squares.put(source, State.of(Piece.EMPTY, Team.NONE));
    }
}
