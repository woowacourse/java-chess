package chess.board;


import chess.piece.Piece;
import chess.piece.Vector;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Point, SquareState> squares = new HashMap<>();
    private boolean onGoing;

    public Board() {
        Point.getAllPoints()
            .forEach(point -> squares.put(point, SquareState.of(Piece.EMPTY, Team.NONE)));
        onGoing = true;
    }

    public void putSymmetrically(Piece piece, Point point) {
        squares.put(point, SquareState.of(piece, Team.WHITE));
        squares.put(point.yAxisOpposite(), SquareState.of(piece, Team.BLACK));
    }

    public SquareState getSquareState(Point point) {
        return squares.get(point);
    }

    public boolean canMove(Point source, Point destination) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);

        return isPawnAndCanMoveDiagonalDirection(source, destination, sourceSquareState)
            || (sourceSquareState.hasMovableVector(source, destination)
            && isValidSourceAndDestination(sourceSquareState, destinationSquareState)
            && isNotBlockedToGo(source, destination, sourceSquareState)
        );
    }

    private boolean isValidSourceAndDestination(
        SquareState sourceSquareState, SquareState destinationSquareState) {
        return sourceSquareState.isNotEmpty() && isNotSameTeam(sourceSquareState,
            destinationSquareState);
    }

    private boolean isPawnAndCanMoveDiagonalDirection(Point source, Point destination,
        SquareState sourceSquareState) {
        return sourceSquareState.isPawn() && canMovePawnToDiagonalDirection(source, destination);
    }

    private boolean canMovePawnToDiagonalDirection(Point source, Point destination) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);
        Vector vector = Vector.get(destination.minusX(source), destination.minusY(source));

        return canWhitePawnKillEnemy(sourceSquareState, destinationSquareState, vector)
            || canBlackPawnKillEnemy(sourceSquareState, destinationSquareState, vector);
    }

    private boolean canWhitePawnKillEnemy(
        SquareState sourceSquareState, SquareState destinationSquareState,
        Vector vector) {
        return sourceSquareState.isTeam(Team.WHITE)
            && destinationSquareState.isTeam(Team.BLACK)
            && vector.isWhiteDiagonalVector();
    }

    private boolean canBlackPawnKillEnemy(
        SquareState sourceSquareState, SquareState destinationSquareState,
        Vector vector) {
        return sourceSquareState.isTeam(Team.BLACK)
            && destinationSquareState.isTeam(Team.WHITE)
            && vector.isBlackDiagonalVector();
    }

    private boolean isNotSameTeam(SquareState sourceSquareState, SquareState destinationSquareState) {
        return destinationSquareState.isNotSameTeam(sourceSquareState);
    }

    private boolean isNotBlockedToGo(Point source, Point destination, SquareState sourceSquareState) {
        Vector vector = sourceSquareState.findMovableVector(source, destination);
        int moveCount = 1;
        boolean unblocked = true;

        for (Point now = source.move(vector); isOnGoing(destination, now) && unblocked;
            now = now.move(vector)) {
            unblocked = underMoveLength(sourceSquareState, moveCount) && squares.get(now).isEmpty();
            moveCount++;
        }
        return unblocked;
    }

    private boolean isOnGoing(Point destination, Point now) {
        return !now.equals(destination);
    }

    private boolean underMoveLength(SquareState sourceSquareState, int moveCount) {
        return moveCount <= sourceSquareState.getMoveLength();
    }

    public void move(Point source, Point destination) {
        if (squares.get(destination).isKing()) {
            onGoing = false;
        }
        squares.put(destination, squares.get(source));
        squares.put(source, SquareState.of(Piece.EMPTY, Team.NONE));
    }

    public boolean isTeam(Point source, Team currentTeam) {
        SquareState squareState = squares.get(source);
        return squareState.isTeam(currentTeam);
    }

    public boolean isOnGoing() {
        return onGoing;
    }
}
