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

    public static final int FIRST_NEXT_MOVE_COUNT = 2;
    private static final double HALF_PAWN_SCORE = 0.5;
    private static final int FIRST_MOVING_PAWN_RANGE = 2;

    private final Map<Point, SquareState> squares = new HashMap<>();
    private boolean isDeadKing;

    public Board() {
        Point.allPoints()
            .forEach(point -> squares.put(point, SquareState.of(Piece.EMPTY, Team.NONE)));
        isDeadKing = false;
    }

    public void putSymmetrically(Piece piece, Point point) {
        squares.put(point, SquareState.of(piece, Team.WHITE));
        squares.put(point.yAxisSymmetricPoint(), SquareState.of(piece, Team.BLACK));
    }

    public void move(Point source, Point destination) {
        if (squares.get(destination).isPieceTypeOf(Piece.KING)) {
            isDeadKing = true;
        }
        squares.put(destination, squares.get(source));
        squares.put(source, SquareState.of(Piece.EMPTY, Team.NONE));
    }

    public boolean canMove(Point source, Point destination, Team currentTeam) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);

        return isValidSourceAndDestination(sourceSquareState, destinationSquareState, currentTeam)
            && sourceSquareState.hasMovableVector(source, destination)
            && isValidPath(source, destination,
            sourceSquareState.movableVector(source, destination))
            && isValidPawnMove(source, destination);
    }

    private boolean isValidSourceAndDestination(
        SquareState sourceSquareState, SquareState destinationSquareState, Team currentTeam) {
        return sourceSquareState.isTeam(currentTeam)
            && destinationSquareState.isNotTeam(currentTeam);
    }

    private boolean isValidPath(Point source, Point destination, MoveVector moveVector) {
        int nextMoveCount = FIRST_NEXT_MOVE_COUNT;
        boolean success = true;

        for (Point now = source.movedPoint(moveVector); isNotArrived(destination, now) && success;
            now = now.movedPoint(moveVector)) {
            success = isWithinMovementRange(source, nextMoveCount, moveVector)
                && squares.get(now).isEmpty();
            nextMoveCount++;
        }
        return success;
    }

    private boolean isNotArrived(Point destination, Point now) {
        return !now.equals(destination);
    }

    private boolean isWithinMovementRange(Point source, int moveCount, MoveVector moveVector) {
        int range = squares.get(source).movementRange();
        if (isFirstStraightMovingPawn(source, moveVector)) {
            range = FIRST_MOVING_PAWN_RANGE;
        }
        return moveCount <= range;
    }

    private boolean isFirstStraightMovingPawn(Point source, MoveVector moveVector) {
        SquareState sourceSquareState = squares.get(source);
        return sourceSquareState.isPieceTypeOf(Piece.PAWN)
            && moveVector.isPawnStraight()
            && (source.isRow(Row.TWO) || source.isRow(Row.SEVEN));
    }

    private boolean isValidPawnMove(Point source, Point destination) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);
        MoveVector moveVector = sourceSquareState.movableVector(source, destination);

        return sourceSquareState.isNotPieceTypeOf(Piece.PAWN) ||
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
        return isDeadKing;
    }

    public double score(Team team) {
        return scoreSum(team) - HALF_PAWN_SCORE * pawnCountInSameColumn(team);
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
            .filter(square -> square.isTeam(team) && square.isPieceTypeOf(Piece.PAWN))
            .count();
    }

    private double scoreSum(Team team) {
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
