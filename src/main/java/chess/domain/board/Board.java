package chess.domain.board;


import chess.domain.piece.Piece;
import chess.domain.piece.Vector;
import chess.dto.BoardDto;

import java.util.*;

public class Board {

    private static final double HALF_PAWN_SCORE = 0.5;

    private final Map<Point, SquareState> squares = new HashMap<>();
    private boolean aliveKing;

    public Board() {
        Point.getAllPoints()
            .forEach(point -> squares.put(point, SquareState.of(Piece.EMPTY, Team.NONE)));
        aliveKing = true;
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

        if (sourceSquareState.isPawn()) {
            return canMovePawn(source, destination);
        }

        return (sourceSquareState.hasMovableVector(source, destination)
            && isValidSourceAndDestination(sourceSquareState, destinationSquareState)
            && isNotBlockedToGo(source, destination, sourceSquareState));
    }

    private boolean canMovePawn(Point source, Point destination) {
        return canMovePawnToStraight(source, destination)
                || canMovePawnToDiagonalDirection(source, destination);
    }

    private boolean canMovePawnToStraight(Point source, Point destination) {
        if (Vector.hasNotVector(destination.minusX(source), destination.minusY(source))) {
            return false;
        }
        Vector vector = Vector.get(destination.minusX(source), destination.minusY(source));

        return canMoveWhitePawnToStraight(source, destination, vector)
            || canMoveBlackPawnToStraight(source, destination, vector);
    }

    private boolean canMoveWhitePawnToStraight(Point source, Point destination, Vector vector) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);

        return sourceSquareState.isTeam(Team.WHITE)
                && vector.isWhitePawnsStraight(source)
                && destinationSquareState.isEmpty();
    }

    private boolean canMoveBlackPawnToStraight(Point source, Point destination, Vector vector) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);

        return sourceSquareState.isTeam(Team.BLACK)
            && vector.isBlackPawnsStraight(source)
            && destinationSquareState.isEmpty();
    }


    private boolean isValidSourceAndDestination(
        SquareState sourceSquareState, SquareState destinationSquareState) {
        return sourceSquareState.isNotEmpty() && isNotSameTeam(sourceSquareState,
            destinationSquareState);
    }

    private boolean canMovePawnToDiagonalDirection(Point source, Point destination) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);
        if (Vector.hasNotVector(destination.minusX(source), destination.minusY(source))) {
            return false;
        }
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

    private boolean isNotSameTeam(SquareState sourceSquareState,
        SquareState destinationSquareState) {
        return destinationSquareState.isNotSameTeam(sourceSquareState);
    }

    private boolean isNotBlockedToGo(Point source, Point destination,
        SquareState sourceSquareState) {
        Vector vector = sourceSquareState.findMovableVector(source, destination);
        int moveCount = 1;
        boolean unblocked = true;

        for (Point now = source.move(vector); isContinued(destination, now) && unblocked;
            now = now.move(vector)) {
            moveCount++;
            unblocked = underMoveLength(sourceSquareState, moveCount) && squares.get(now).isEmpty();
        }
        return unblocked;
    }

    private boolean isContinued(Point destination, Point now) {
        return !now.equals(destination);
    }

    private boolean underMoveLength(SquareState sourceSquareState, int moveCount) {
        return moveCount <= sourceSquareState.getMoveLength();
    }

    public void move(Point source, Point destination) {
        if (squares.get(destination).isKing()) {
            aliveKing = !aliveKing;
        }
        squares.put(destination, squares.get(source));
        squares.put(source, SquareState.of(Piece.EMPTY, Team.NONE));
    }

    public boolean isTeam(Point source, Team currentTeam) {
        SquareState squareState = squares.get(source);
        return squareState.isTeam(currentTeam);
    }

    public boolean isContinued() {
        return aliveKing;
    }

    public double score(Team team) {
        return sumScore(team) - HALF_PAWN_SCORE * pawnCountInSameColumn(team);
    }

    private long pawnCountInSameColumn(Team team) {
        return Arrays.stream(Column.values()).mapToLong(column ->
            pawnCountInColumn(team, column))
            .filter(count -> count >= 2)
            .sum();
    }

    private long pawnCountInColumn(Team team, Column column) {
        return squares.keySet().stream()
            .filter(point -> point.isColumn(column))
            .map(squares::get)
            .filter(square -> square.isTeam(team) && square.isPawn())
            .count();
    }

    private double sumScore(Team team) {
        return squares.values().stream()
            .filter(square -> square.isTeam(team))
            .mapToDouble(SquareState::score)
            .sum();
    }

    public BoardDto generateBoardDto() {
        List<List<String>> result = new ArrayList<>();
        for (Row row : Row.reverseRows()) {
            result.add(generateRow(row));
        }

        return new BoardDto(result);
    }

    private List<String> generateRow(Row row) {
        List<String> rowResult = new ArrayList<>();
        for (Column column : Column.columns()) {
            SquareState squareState = squares
                .get(Point.of(column.getXCoordinate() + row.getYCoordinate()));
            rowResult.add(squareState.pieceName());
        }
        return rowResult;
    }
}
