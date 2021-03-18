package chess.board;


import chess.piece.Piece;
import chess.piece.Vector;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Point, State> squares = new HashMap<>();
    private boolean onGoing;

    public Board() {
        Point.getAllPoints()
            .forEach(point -> squares.put(point, State.of(Piece.EMPTY, Team.NONE)));
        onGoing = true;
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

        return isPawnAndCanMoveDiagonalDirection(source, destination, sourceState)
            || (sourceState.hasMovableVector(source, destination)
            && isValidSourceAndDestination(sourceState, destinationState)
            && isNotBlockedToGo(source, destination, sourceState)
            );
    }

    private boolean isValidSourceAndDestination(State sourceState, State destinationState) {
        return sourceState.isNotEmpty() && isNotSameTeam(sourceState, destinationState);
    }

    private boolean isPawnAndCanMoveDiagonalDirection(Point source, Point destination,
        State sourceState) {
        return sourceState.isPawn() && canMovePawnToDiagonalDirection(source, destination);
    }

    private boolean canMovePawnToDiagonalDirection(Point source, Point destination) {
        State sourceState = squares.get(source);
        State destinationState = squares.get(destination);
        Vector vector = Vector.get(destination.minusX(source), destination.minusY(source));

        return canWhitePawnKillEnemy(sourceState, destinationState, vector)
            || canBlackPawnKillEnemy(sourceState, destinationState, vector);
    }

    private boolean canWhitePawnKillEnemy(State sourceState, State destinationState,
        Vector vector) {
        return sourceState.isTeam(Team.WHITE)
            && destinationState.isTeam(Team.BLACK)
            && vector.isWhiteDiagonalVector();
    }

    private boolean canBlackPawnKillEnemy(State sourceState, State destinationState,
        Vector vector) {
        return sourceState.isTeam(Team.BLACK)
            && destinationState.isTeam(Team.WHITE)
            && vector.isBlackDiagonalVector();
    }

    private boolean isNotSameTeam(State sourceState, State destinationState) {
        return destinationState.isNotSameTeam(sourceState);
    }

    private boolean isNotBlockedToGo(Point source, Point destination, State sourceState) {
        Vector vector = sourceState.findMovableVector(source, destination);
        int moveCount = 1;
        boolean unblocked = true;

        for (Point now = source.move(vector); isOnGoing(destination, now) && unblocked;
            now = now.move(vector)) {
            unblocked = underMoveLength(sourceState, moveCount) && squares.get(now).isEmpty();
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
        if (squares.get(destination).isKing()) {
            onGoing = false;
        }
        squares.put(destination, squares.get(source));
        squares.put(source, State.of(Piece.EMPTY, Team.NONE));
    }

    public boolean isTeam(Point source, Team currentTeam) {
        State state = squares.get(source);
        return state.isTeam(currentTeam);
    }

    public boolean isOnGoing() {
        return onGoing;
    }
}
