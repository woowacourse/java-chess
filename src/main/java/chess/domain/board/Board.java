package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
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

    public static Board getBasicInstance() {
        return new Board(new BasicChessBoardGenerator().generator());
    }

    public boolean exist(Position position, Class<? extends Piece> type, Color color) {
        Piece piece = value.get(position);
        return piece.isSameType(type) && piece.isSameColor(color);
    }

    public Piece get(Position position) {
        return value.get(position);
    }

    public Optional<Piece> findPieceBy(Position position) {
        Piece piece = value.get(position);
        if (piece == null) {
            return Optional.empty();
        }
        return Optional.of(piece);
    }

    public void move(Position src, Position dest) {
        Piece piece = findPieceBy(src)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다"));

        if (!piece.canMove(src, dest)) {
            throw new IllegalArgumentException("이동할 수 없습니다");
        }

        checkSameColorInDestination(piece, dest);
        checkObstacleInPath(src, dest);

        value.put(dest, piece);
        value.remove(src);
    }

    public void move(Position src, Position dest, Color color) {
        Piece piece = findPieceBy(src)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다"));

        if (!piece.isSameColor(color)) {
            throw new IllegalArgumentException("상대방의 말을 움직일 수 없습니다");
        }

        move(src, dest);
    }

    private void checkSameColorInDestination(Piece piece, Position dest) {
        Optional<Piece> optionalPiece = findPieceBy(dest);
        if (optionalPiece.isPresent() && piece.isSameColor(optionalPiece.get())) {
            throw new IllegalArgumentException("도착 위치에 아군이 있어 이동할 수 없습니다.");
        }
    }

    private void checkObstacleInPath(Position src, Position dest) {
        Piece piece = findPieceBy(src)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다"));

        Direction direction = piece.findDirection(src, dest);
        checkObstacle(src, dest, direction);
    }

    private void checkObstacle(Position src, Position dest, Direction direction) {
        Position nextPosition = src.move(direction.getX(), direction.getY());

        while (!nextPosition.equals(dest)) {
            checkObstacleIn(nextPosition);
            nextPosition = nextPosition.move(direction.getX(), direction.getY());
        }
    }

    private void checkObstacleIn(Position src) {
        if (findPieceBy(src).isPresent()) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    public double calculateScore(Color color) {
        return Arrays.stream(Column.values())
                .map(this::getPositionsByColumn)
                .mapToDouble(positions -> calculateScoreByColumn(positions, color))
                .sum();
    }

    private double calculateScoreByColumn(List<Position> columns, Color color) {
        double result = 0.0;

        for (Position column : columns) {
            Optional<Piece> optionalPiece = findPieceBy(column);
            if (optionalPiece.isPresent() && optionalPiece.get().isSameColor(color)) {
                result += optionalPiece.get().getPoint();
            }
        }

        int pawnCount = countPawn(columns, color);
        if (pawnCount > 1) {
            result -= pawnCount * 0.5;
        }

        return result;
    }

    private int countPawn(List<Position> columns, Color color) {
        return (int) columns.stream()
                .map(this::get)
                .filter(Objects::nonNull)
                .filter(piece -> piece.isSameColor(color))
                .filter(piece -> piece.isSameType(Pawn.class))
                .count();
    }

    private List<Position> getPositionsByColumn(Column column) {
        return Arrays.stream(Row.values())
                .map(row -> new Position(column, row))
                .collect(Collectors.toList());
    }

    public Map<Position, Piece> getValue() {
        return Collections.unmodifiableMap(value);
    }

    public boolean hasKing(Color color) {
        return value.containsValue(new King(color));
    }
}
