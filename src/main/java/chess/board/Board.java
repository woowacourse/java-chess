package chess.board;


import chess.piece.Vector;
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
        squares.put(point.yAxisOpposite(), State.of(piece, Team.BLACK));
    }

    public State getState(Point point) {
        return squares.get(point);
    }

    public boolean canMove(Point source, Point destination) {
        State sourceState = squares.get(source);
        State destinationState = squares.get(destination);
        if (sourceState.isPawn() && canMovePawn(source, destination)) {
            return true;
        }
        Vector vector = sourceState.findMovableVector(source, destination);
        if (vector == null) {
            return false;
        }

        return sourceState.isNotEmpty()
            && isNotSameTeam(sourceState, destinationState)
            && isNotBlockedToGo(source, destination, vector);
    }

    private boolean canMovePawn(Point source, Point destination) {
        State sourceState = squares.get(source);
        State destinationState = squares.get(destination);
        Vector vector = Vector.get(destination.minusX(source), destination.minusY(source));

        if (sourceState.isTeam(Team.WHITE) && destinationState.isTeam(Team.BLACK)) {
            return (vector == Vector.NORTHEAST || vector == Vector.NORTHWEST);
        }
        if (sourceState.isTeam(Team.BLACK) && destinationState.isTeam(Team.WHITE)) {
            return (vector == Vector.SOUTHEAST || vector == Vector.SOUTHWEST);
        }
        return false;
    }

    private boolean isNotSameTeam(State sourceState, State destinationState) {
        return destinationState.isNotSameTeam(sourceState);
    }

    private boolean isNotBlockedToGo(Point source, Point destination, Vector vector) {
        State sourceState = squares.get(source);

        int moveCount = 1;
        boolean unblocked = true;
        for (Point now = source.move(vector); isOnGoing(destination, now) && unblocked;
            now = now.move(vector)) {
            unblocked =
                underMoveLength(sourceState, moveCount) && squares.get(now).isEmpty();

            moveCount++;
        }
        return unblocked;
    }

    private boolean isOnGoing(Point destination, Point now) {
        return !now.equals(destination);
    }

    private boolean underMoveLength(State sourceState, int moveCount) {
        return moveCount <= sourceState.getMoveLength();
    }

    public void move(Point source, Point destination) {
        squares.put(destination, squares.get(source));
        squares.put(source, State.of(Piece.EMPTY, Team.NONE));
    }
}
