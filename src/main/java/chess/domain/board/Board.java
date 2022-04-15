package chess.domain.board;

import chess.domain.position.Column;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Result;
import chess.domain.position.Row;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> value;

    public Board(Map<Position, Piece> value) {
        this.value = value;
    }

    public static Board getInitializedInstance() {
        return new Board(PieceFactory.createInitializedChessBoard());
    }

    public boolean exist(Position position, Class<? extends Piece> type, Color color) {
        Piece piece = value.get(position);
        if (piece == null) {
            return false;
        }
        return piece.isSameType(type) && piece.isSameColor(color);
    }

    public Optional<Piece> findPieceBy(Position position) {
        Piece piece = value.get(position);
        if (piece == null) {
            return Optional.empty();
        }
        return Optional.of(piece);
    }

    public void move(Position src, Position dest, Color targetColor) {
        Piece piece = findPieceBy(src)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다"));
        checkCurrentPieceAndTargetPieceHaveSameColor(targetColor, piece);
        move(src, dest, piece);
    }

    private void checkCurrentPieceAndTargetPieceHaveSameColor(Color targetColor, Piece piece) {
        if (!piece.isSameColor(targetColor)) {
            throw new IllegalArgumentException("상대방의 말을 움직일 수 없습니다");
        }
    }

    public Result calculateCurrentResult() {
        return Result.calculateResult(calculateScore(Color.WHITE), calculateScore(Color.BLACK));
    }

    public double calculateScore(Color color) {
        return Arrays.stream(Column.values())
                .map(this::getColumns)
                .mapToDouble(positions -> calculateScoreByColumn(positions, color))
                .sum();
    }

    public boolean hasKing(Color color) {
        return value.containsValue(new King(color));
    }

    public Map<Position, Piece> getValue() {
        return Collections.unmodifiableMap(value);
    }

    private void move(Position src, Position dest, Piece piece) {
        checkFollowMovementRule(src, dest, piece);
        checkPawnMoveForwardToCatch(src, dest, piece);
        checkPawnMoveDiagonallyWithoutCatching(src, dest, piece);
        checkSameColorInDestination(piece, dest);
        checkObstacleInPath(src, dest, piece);

        value.put(dest, piece);
        value.remove(src);
    }

    private void checkFollowMovementRule(Position src, Position dest, Piece piece) {
        if (!piece.canMove(src, dest)) {
            throw new IllegalArgumentException("이동할 수 없습니다");
        }
    }

    private void checkPawnMoveForwardToCatch(Position src, Position dest, Piece piece) {
        boolean destHasPiece = findPieceBy(dest).isPresent();
        if (piece.isSameType(Pawn.class) && destHasPiece && src.isSameFile(dest)) {
            throw new IllegalArgumentException("이동할 수 없습니다");
        }
    }

    private void checkPawnMoveDiagonallyWithoutCatching(Position src, Position dest, Piece piece) {
        boolean destHasPiece = findPieceBy(dest).isPresent();
        if (piece.isSameType(Pawn.class) && !destHasPiece && !src.isSameFile(dest)) {
            throw new IllegalArgumentException("이동할 수 없습니다");
        }
    }

    private void checkSameColorInDestination(Piece piece, Position dest) {
        Optional<Piece> optionalPiece = findPieceBy(dest);
        if (optionalPiece.isPresent() && piece.isSameColor(optionalPiece.get())) {
            throw new IllegalArgumentException("도착 위치에 아군이 있어 이동할 수 없습니다.");
        }
    }

    private void checkObstacleInPath(Position src, Position dest, Piece piece) {
        Direction direction = piece.findDirection(src, dest);
        checkObstacle(src, dest, direction);
    }

    private void checkObstacle(Position src, Position dest, Direction direction) {
        Position nextPosition = src.moveTo(direction.getX(), direction.getY());

        while (!nextPosition.equals(dest)) {
            checkObstacleIn(nextPosition);
            nextPosition = nextPosition.moveTo(direction.getX(), direction.getY());
        }
    }

    private void checkObstacleIn(Position src) {
        if (findPieceBy(src).isPresent()) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private double calculateScoreByColumn(List<Position> columns, Color color) {
        double result = 0.0;

        for (Position column : columns) {
            result = addPoint(color, result, column);
        }
        result = handlePawnPenalty(result, countPawn(columns, color));
        return result;
    }

    private double addPoint(Color color, double result, Position column) {
        Optional<Piece> optionalPiece = findPieceBy(column);
        if (optionalPiece.isPresent() && optionalPiece.get().isSameColor(color)) {
            result += optionalPiece.get().getPoint();
        }
        return result;
    }

    private double handlePawnPenalty(double result, int pawnCount) {
        if (pawnCount > 1) {
            result -= pawnCount * 0.5;
        }
        return result;
    }

    private int countPawn(List<Position> columns, Color color) {
        return (int) columns.stream()
                .map(value::get)
                .filter(Objects::nonNull)
                .filter(piece -> piece.isSameColor(color))
                .filter(piece -> piece.isSameType(Pawn.class))
                .count();
    }

    private List<Position> getColumns(Column column) {
        return Arrays.stream(Row.values())
                .map(row -> new Position(column, row))
                .collect(Collectors.toList());
    }
}
