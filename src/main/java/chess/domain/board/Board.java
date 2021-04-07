package chess.domain.board;

import chess.domain.piece.MoveVector;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private final Map<Point, SquareState> squares;

    public Board() {
        squares = new HashMap<>();
        clear();
    }

    public Board(Map<Point, SquareState> squares) {
        this.squares = new HashMap<>(squares);
    }

    public void clear() {
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

    public boolean hasSamePieceTypeAt(Point point, Piece piece) {
        return squares.get(point).isPieceTypeOf(piece);
    }

    public boolean hasNotSamePieceTypeAt(Point point, Piece piece) {
        return !hasSamePieceTypeAt(point, piece);
    }

    public boolean hasSameTeamAt(Point point, Team team) {
        return squares.get(point).isOnTeam(team);
    }

    public boolean hasNotSameTeamAt(Point point, Team team) {
        return !hasSameTeamAt(point, team);
    }

    public boolean hasEnemy(Point source, Point destination) {
        SquareState sourceState = squares.get(source);
        SquareState destinationState = squares.get(destination);

        return sourceState.isEnemyOf(destinationState);
    }

    public boolean hasMovableVector(Point source, Point destination) {
        SquareState sourceState = squares.get(source);

        return sourceState.hasMovableVector(source, destination);
    }

    public Team teamAt(Point point) {
        return squares.get(point).team();
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
            .filter(point -> point.isLocatedIn(column))
            .map(squares::get)
            .filter(square -> square.isOnTeam(team) && square.isPieceTypeOf(Piece.PAWN))
            .count();
    }

    public List<SquareState> AllSquaresFrom(Team team) {
        return squares.values().stream()
            .filter(state -> state.isOnTeam(team))
            .collect(Collectors.toList());
    }

    public Map<Point, SquareState> squares() {
        return new HashMap<>(squares);
    }

    public List<Point> points() {
        return new ArrayList<>(squares.keySet());
    }
}
