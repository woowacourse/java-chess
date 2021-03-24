package chess.domain.board;

import chess.domain.piece.MoveVector;
import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private final Map<Point, SquareState> squares = new HashMap<>();

    public Board() {
        Point.allPoints()
            .forEach(point -> squares.put(point, SquareState.of(Piece.EMPTY, Team.NONE)));
    }

    public void putSymmetrically(Piece piece, Point point) {
        squares.put(point, SquareState.of(piece, Team.WHITE));
        squares.put(point.yAxisSymmetricPoint(), SquareState.of(piece, Team.BLACK));
    }

    public void move(Point source, Point destination) {
        squares.put(destination, squares.get(source));
        squares.put(source, SquareState.of(Piece.EMPTY, Team.NONE));
    }

    public boolean isSamePieceTypeAt(Point point, Piece piece) {
        return squares.get(point).isPieceTypeOf(piece);
    }

    public boolean isNotSamePieceTypeAt(Point point, Piece piece) {
        return !isSamePieceTypeAt(point, piece);
    }

    public boolean isSameTeamAt(Point point, Team team) {
        return squares.get(point).isTeam(team);
    }

    public boolean isNotSameTeamAt(Point point, Team team) {
        return !isSameTeamAt(point, team);
    }

    public boolean isEnemy(Point source, Point destination) {
        SquareState sourceState = squares.get(source);
        SquareState destinationState = squares.get(destination);

        return sourceState.isEnemy(destinationState);
    }

    public Team teamAt(Point point) {
        return squares.get(point).team();
    }

    public boolean hasMovableVector(Point source, Point destination) {
        SquareState sourceState = squares.get(source);

        return sourceState.hasMovableVector(source, destination);
    }

    public MoveVector movableVector(Point source, Point destination) {
        SquareState sourceState = squares.get(source);

        return sourceState.movableVector(source, destination);
    }

    public int movementRange(Point point) {
        return squares.get(point).movementRange();
    }

    public long pawnCountInColumn(Team team, Column column) {
        return squares.keySet().stream()
            .filter(point -> point.isColumn(column))
            .map(squares::get)
            .filter(square -> square.isTeam(team) && square.isPieceTypeOf(Piece.PAWN))
            .count();
    }

    public List<SquareState> AllSquaresFrom(Team team) {
        return squares.values().stream()
            .filter(state -> state.isTeam(team))
            .collect(Collectors.toList());
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
