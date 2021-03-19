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

    private final Map<Point, SquareState> squares = new HashMap<>();
    private boolean deadKing;

    public Board() {
        Point.getAllPoints()
            .forEach(point -> squares.put(point, SquareState.of(Piece.EMPTY, Team.NONE)));
        deadKing = false;
    }

    public void putSymmetrically(Piece piece, Point point) {
        squares.put(point, SquareState.of(piece, Team.WHITE));
        squares.put(point.yAxisOpposite(), SquareState.of(piece, Team.BLACK));
    }

    public SquareState squareState(Point point) {
        return squares.get(point);
    }

    public boolean canMove(Point source, Point destination, Team currentTeam) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);

        return isValidSourceAndDestination(sourceSquareState, destinationSquareState, currentTeam)
            && ((sourceSquareState.isPawn() && canMovePawn(source, destination))
            || (!sourceSquareState.isPawn()
            && sourceSquareState.hasMovableVector(source, destination)
            && isNotBlockedToGo(source, destination, sourceSquareState)));
    }

    private boolean canMovePawn(Point source, Point destination) {
        return canMovePawnToStraight(source, destination) || canMovePawnToDiagonalDirection(source,
            destination);
    }

    private boolean canMovePawnToStraight(Point source, Point destination) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);
        if (MoveVector.hasNotMoveVector(destination.minusX(source), destination.minusY(source))) {
            return false;
        }
        MoveVector moveVector = MoveVector
            .get(destination.minusX(source), destination.minusY(source));

        return canMoveWhitePawnToStraight(sourceSquareState, destinationSquareState, moveVector)
            || canMoveBlackPawnToStraight(sourceSquareState, destinationSquareState, moveVector);
    }

    private boolean canMoveBlackPawnToStraight(SquareState sourceSquareState,
        SquareState destinationSquareState, MoveVector moveVector) {
        return sourceSquareState.isTeam(Team.BLACK)
            && moveVector.isBlackPawnsStraight()
            && destinationSquareState.isEmpty();
    }

    private boolean canMoveWhitePawnToStraight(SquareState sourceSquareState,
        SquareState destinationSquareState, MoveVector moveVector) {
        return sourceSquareState.isTeam(Team.WHITE)
            && moveVector.isWhitePawnsStraight()
            && destinationSquareState.isEmpty();
    }


    private boolean isValidSourceAndDestination(
        SquareState sourceSquareState, SquareState destinationSquareState, Team currentTeam) {
        return sourceSquareState.isTeam(currentTeam) && isNotSameTeam(sourceSquareState,
            destinationSquareState);
    }

    private boolean canMovePawnToDiagonalDirection(Point source, Point destination) {
        SquareState sourceSquareState = squares.get(source);
        SquareState destinationSquareState = squares.get(destination);
        if (MoveVector.hasNotMoveVector(destination.minusX(source), destination.minusY(source))) {
            return false;
        }
        MoveVector moveVector = MoveVector
            .get(destination.minusX(source), destination.minusY(source));

        return canWhitePawnKillEnemy(sourceSquareState, destinationSquareState, moveVector)
            || canBlackPawnKillEnemy(sourceSquareState, destinationSquareState, moveVector);
    }

    private boolean canWhitePawnKillEnemy(
        SquareState sourceSquareState, SquareState destinationSquareState,
        MoveVector moveVector) {
        return sourceSquareState.isTeam(Team.WHITE)
            && destinationSquareState.isTeam(Team.BLACK)
            && moveVector.isWhiteDiagonalVector();
    }

    private boolean canBlackPawnKillEnemy(
        SquareState sourceSquareState, SquareState destinationSquareState,
        MoveVector moveVector) {
        return sourceSquareState.isTeam(Team.BLACK)
            && destinationSquareState.isTeam(Team.WHITE)
            && moveVector.isBlackDiagonalVector();
    }

    private boolean isNotSameTeam(SquareState sourceSquareState,
        SquareState destinationSquareState) {
        return destinationSquareState.isNotSameTeam(sourceSquareState);
    }

    private boolean isNotBlockedToGo(Point source, Point destination,
        SquareState sourceSquareState) {
        MoveVector moveVector = sourceSquareState.findMovableVector(source, destination);
        int moveCount = 1;
        boolean unblocked = true;

        for (Point now = source.move(moveVector); isContinued(destination, now) && unblocked;
            now = now.move(moveVector)) {
            unblocked = underMoveLength(sourceSquareState, moveCount) && squares.get(now).isEmpty();
            moveCount++;
        }
        return unblocked;
    }

    private boolean isContinued(Point destination, Point now) {
        return !now.equals(destination);
    }

    private boolean underMoveLength(SquareState sourceSquareState, int moveCount) {
        return moveCount <= sourceSquareState.getMoveLength();
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
        for (Row row : Row.reverseRows()) {
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
