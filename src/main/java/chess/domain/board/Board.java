package chess.domain.board;

import chess.domain.piece.MoveVector;
import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final double HALF_PAWN_SCORE = 0.5;
    private static final int FIRST_MOVING_PAWN_LENGTH = 2;
    public static final int FIRST_NEXT_MOVE_COUNT = 2;

    private final Map<Point, SquareState> squares = new HashMap<>();
    private boolean deadKing;

    public Board() {
        Point.AllPoints()
            .forEach(point -> squares.put(point, SquareState.of(Piece.EMPTY, Team.NONE)));
        deadKing = false;
    }

    public void putSymmetrically(Piece piece, Point point) {
        squares.put(point, SquareState.of(piece, Team.WHITE));
        squares.put(point.yAxisOppositePoint(), SquareState.of(piece, Team.BLACK));
    }

    public boolean canMove(Point source, Point destination, Team currentTeam) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);

        return isValidSourceAndDestination(sourceSquareState, destinationSquareState, currentTeam)
            && sourceSquareState.hasMovableVector(source, destination)
            && isValidPath(source, destination)
            && isValidPawnMove(source, destination);
    }

    private boolean isValidSourceAndDestination(
        SquareState sourceSquareState, SquareState destinationSquareState, Team currentTeam) {
        return sourceSquareState.isTeam(currentTeam)
            && destinationSquareState.isNotTeam(currentTeam);
    }

    private boolean isValidPath(Point source, Point destination) {
        SquareState sourceSquareState = squares.get(source);
        MoveVector moveVector = sourceSquareState.movableVector(source, destination);
        int nextMoveCount = FIRST_NEXT_MOVE_COUNT;
        boolean success = true;

        for (Point now = source.movedPoint(moveVector); isNotArrived(destination, now) && success;
            now = now.movedPoint(moveVector)) {
            success =
                underMoveLength(source, nextMoveCount, moveVector) && squares.get(now).isEmpty();
            nextMoveCount++;
        }
        return success;
    }

    private boolean isNotArrived(Point destination, Point now) {
        return !now.equals(destination);
    }

    private boolean underMoveLength(Point source, int moveCount, MoveVector moveVector) {
        int length = squares.get(source).getMoveLength();
        if (firstStraightMovingPawnCondition(source, moveVector)) {
            length = FIRST_MOVING_PAWN_LENGTH;
        }
        return moveCount <= length;
    }

    private boolean firstStraightMovingPawnCondition(Point source, MoveVector moveVector) {
        SquareState sourceSquareState = squares.get(source);
        return sourceSquareState.isPawn()
            && moveVector.isPawnStraight()
            && (source.isRow(Row.TWO) || source.isRow(Row.SEVEN));
    }

    private boolean isValidPawnMove(Point source, Point destination) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);
        MoveVector moveVector = sourceSquareState.movableVector(source, destination);

        return !sourceSquareState.isPawn() ||
            (isValidPawnStraightMove(destinationSquareState, moveVector)
                && isValidPawnDiagonalMove(sourceSquareState, destinationSquareState, moveVector));
    }

    private boolean isValidPawnStraightMove(SquareState destination, MoveVector moveVector) {
        if (moveVector.isPawnStraight()) {
            return destination.isEmpty();
        }
        return true;
    }

    private boolean isValidPawnDiagonalMove(SquareState source, SquareState destination,
        MoveVector moveVector) {
        if (moveVector.isDiagonalVector()) {
            return destination.isEnemy(source);
        }
        return true;
    }

    public boolean isKingDead() {
        return deadKing;
    }

    public void move(Point source, Point destination) {
        if (squares.get(destination).isKing()) {
            deadKing = true;
        }
        squares.put(destination, squares.get(source));
        squares.put(source, SquareState.of(Piece.EMPTY, Team.NONE));
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

    public BoardDto boardDto() {
        List<List<String>> result = new ArrayList<>();
        for (Row row : Row.reversedRows()) {
            result.add(rowDto(row));
        }

        return new BoardDto(result);
    }

    private List<String> rowDto(Row row) {
        List<String> rowResult = new ArrayList<>();
        for (Column column : Column.columns()) {
            SquareState squareState = squares.get(Point.of(column.coordinate() + row.coordinate()));
            rowResult.add(squareState.pieceName());
        }
        return rowResult;
    }
}
