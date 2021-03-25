package chess.domain.board;

import chess.domain.piece.Vector;

import java.util.Map;

public class PieceMovement {
    private static final int ONE_MOVE_COUNT = 1;

    private final Map<Point, Square> squares;
    private Square sourceSquare;
    private Square destinationSquare;
    private Vector vector;

    public PieceMovement(Map<Point, Square> squares) {
        this.squares = squares;
    }

    public void validateMovement(Point source, Point destination, Team team) {
        setSquareAndVector(source, destination);
        validateTurn(team);
        validatePoint(source, destination);
    }

    private void validatePoint(Point source, Point destination) {
        if (sourceSquare.isEmpty()) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
        if (!isValidMove(source, destination)) {
            throw new IllegalArgumentException("해당 위치로는 움직일 수 없습니다.");
        }
    }

    private void validateTurn(Team currentTeam) {
        if (!sourceSquare.isTeam(currentTeam)) {
            throw new IllegalArgumentException("현재 플레이어의 기물이 아닙니다.");
        }
    }

    private boolean isValidMove(Point source, Point destination) {
        if (sourceSquare.isPawn()) {
            return canMovePawn(source, destination);
        }

        return isValidSourceAndDestination() && isValidPath(source, destination);
    }

    private boolean isValidSourceAndDestination() {
        return sourceSquare.isNotEmpty() && isNotSameTeam(sourceSquare, destinationSquare);
    }

    private boolean isNotSameTeam(Square sourceSquare, Square destinationSquare) {
        return destinationSquare.isNotSameTeam(sourceSquare);
    }

    private boolean isValidPath(Point source, Point destination) {
        int moveCount = ONE_MOVE_COUNT;
        boolean unblocked = true;

        for (Point now = source.move(vector); isGoing(destination, now) && unblocked;
             now = now.move(vector)) {
            unblocked = underMoveLength(sourceSquare, moveCount) && squares.get(now).isEmpty();
            moveCount++;
        }
        return unblocked;
    }

    private boolean isGoing(Point destination, Point now) {

        return !now.equals(destination);
    }

    private boolean underMoveLength(Square sourceSquare, int moveCount) {
        return moveCount <= sourceSquare.getMoveLength();
    }

    private boolean canMovePawn(Point source, Point destination) {

        return canMovePawnToDiagonalDirection() || canMovePawnToStraight(source, destination);
    }

    private boolean canMovePawnToDiagonalDirection() {

        return canWhitePawnKillEnemy(sourceSquare, destinationSquare, vector)
                || canBlackPawnKillEnemy(sourceSquare, destinationSquare, vector);
    }

    private boolean canMovePawnToStraight(Point source, Point destination) {

        return canMoveWhitePawnToStraight(source, destination, vector)
                || canMoveBlackPawnToStraight(source, destination, vector);
    }

    private boolean canMoveWhitePawnToStraight(Point source, Point destination, Vector vector) {

        return sourceSquare.isTeam(Team.WHITE)
                && vector.isWhitePawnsStraight()
                && isValidPath(source, destination)
                && destinationSquare.isEmpty();
    }

    private boolean canMoveBlackPawnToStraight(Point source, Point destination, Vector vector) {

        return sourceSquare.isTeam(Team.BLACK)
                && vector.isBlackPawnsStraight()
                && isValidPath(source, destination)
                && destinationSquare.isEmpty();
    }

    private boolean canWhitePawnKillEnemy(Square sourceSquare, Square destinationSquare, Vector vector) {

        return sourceSquare.isTeam(Team.WHITE)
                && destinationSquare.isTeam(Team.BLACK)
                && vector.isWhiteDiagonalVector();
    }

    private boolean canBlackPawnKillEnemy(Square sourceSquare, Square destinationSquare, Vector vector) {

        return sourceSquare.isTeam(Team.BLACK)
                && destinationSquare.isTeam(Team.WHITE)
                && vector.isBlackDiagonalVector();
    }

    private void setSquareAndVector(Point source, Point destination) {
        sourceSquare = squares.get(source);
        destinationSquare = squares.get(destination);
        vector = sourceSquare.findMovableVector(source, destination);
    }
}
