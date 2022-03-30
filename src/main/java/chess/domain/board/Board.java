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

    private static final double PAWN_HALF_POINT = 0.5;
    private static final int PAWN_PENALTY_COUNT_IN_SAME_FILE = 2;

    private final Map<Position, Piece> value;

    public Board(Map<Position, Piece> value) {
        this.value = value;
    }

    public Optional<Piece> findPieceBy(Position position) {
        Piece piece = value.get(position);
        if (piece == null) {
            return Optional.empty();
        }
        return Optional.of(piece);
    }

    public void move(Position source, Position destination, Color color) {
        Piece piece = findPieceBy(source)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다"));

        if (!piece.isSameColor(color)) {
            throw new IllegalArgumentException("상대방의 말을 움직일 수 없습니다");
        }

        move(source, destination);
    }

    private void move(Position source, Position destination) {
        Piece piece = findPieceBy(source)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다"));

        checkCanMove(piece, source, destination);
        checkSameColorIndestinationination(piece, destination);
        checkObstacleInPath(source, destination);

        value.put(destination, piece);
        value.remove(source);
    }

    private void checkCanMove(Piece piece, Position source, Position destination) {
        validatePawnCatchFront(piece, source, destination);
        validatePawnCannotCatchDiagonal(piece, source, destination);
        if (!piece.canMove(source, destination)) {
            throw new IllegalArgumentException("이동할 수 없습니다");
        }
    }

    private void validatePawnCatchFront(Piece piece, Position source, Position destination) {
        boolean destinationHasPiece = findPieceBy(destination).isPresent();
        if (piece.isSameType(Pawn.class) && destinationHasPiece && source.isSameFile(destination)) {
            throw new IllegalArgumentException("폰은 직진으로 기물을 잡을 수 없습니다");
        }
    }

    private void validatePawnCannotCatchDiagonal(Piece piece, Position source, Position destination) {
        boolean destinationHasPiece = findPieceBy(destination).isPresent();
        if (piece.isSameType(Pawn.class) && !destinationHasPiece && !source.isSameFile(destination)) {
            throw new IllegalArgumentException("폰은 기물을 잡을 수 있을때만 대각선으로 이동할 수 있습니다");
        }
    }

    private void checkSameColorIndestinationination(Piece piece, Position destination) {
        Optional<Piece> optionalPiece = findPieceBy(destination);
        if (optionalPiece.isPresent() && piece.isSameColor(optionalPiece.get())) {
            throw new IllegalArgumentException("도착 위치에 아군이 있어 이동할 수 없습니다.");
        }
    }

    private void checkObstacleInPath(Position source, Position destination) {
        Piece piece = findPieceBy(source)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다"));

        Direction direction = piece.findDirection(source, destination);
        checkObstacle(source, destination, direction);
    }

    private void checkObstacle(Position source, Position destination, Direction direction) {
        Position nextPosition = source.move(direction.getX(), direction.getY());

        while (!nextPosition.equals(destination)) {
            checkObstacleIn(nextPosition);
            nextPosition = nextPosition.move(direction.getX(), direction.getY());
        }
    }

    private void checkObstacleIn(Position source) {
        if (findPieceBy(source).isPresent()) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    public double calculateScore(Color color) {
        return Arrays.stream(File.values())
                .map(this::getPositionsByColumn)
                .mapToDouble(positions -> calculateScoreByColumn(positions, color))
                .sum();
    }

    private double calculateScoreByColumn(List<Position> positions, Color color) {
        double result = positions.stream()
                .map(value::get)
                .filter(Objects::nonNull)
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getPoint)
                .sum();

        result = subtractPawnPenalty(result, countPawn(positions, color));
        return result;
    }

    private double subtractPawnPenalty(double result, int pawnCount) {
        if (pawnCount >= PAWN_PENALTY_COUNT_IN_SAME_FILE) {
            result -= pawnCount * PAWN_HALF_POINT;
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

    private List<Position> getPositionsByColumn(File file) {
        return Arrays.stream(Rank.values())
                .map(row -> new Position(file, row))
                .collect(Collectors.toList());
    }

    public Map<Position, Piece> getValue() {
        return Collections.unmodifiableMap(value);
    }

    public boolean hasKing(Color color) {
        return value.containsValue(new King(color));
    }
}
