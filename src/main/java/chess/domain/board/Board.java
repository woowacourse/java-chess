package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Board {
    public static final int BOUNDARY = 8;
    private static final String EMPTY = ".";

    private Map<Point, Optional<Piece>> points;
    private boolean isKingDead;

    public Board(Map<Point, Optional<Piece>> points) {
        this.points = new HashMap<>(points);
        this.isKingDead = false;
    }

    public Optional<Piece> get(Point point) {
        return points.get(point);
    }

    public boolean move(Point prev, Point next) {
        if (canMove(prev, next) && points.get(prev).get().isMovable(prev, next)) {

            Optional<Piece> nextPiece = points.get(next);
            updateKingStatus(nextPiece);

            points.put(next, points.get(prev));
            points.put(prev, Optional.empty());

            return true;
        }
        return false;
    }

    private void updateKingStatus(Optional<Piece> nextPiece) {
        if (nextPiece.isPresent() && PieceType.of(nextPiece.get().pieceToString()).isKing()) {
            isKingDead = true;
        }
    }

    private boolean canMove(Point prev, Point next) {
        if (prev == next) {
            return false;
        }
        Optional<Piece> prevPiece = points.get(prev);
        if (!prevPiece.isPresent()) {
            return false;
        }

        if (!PieceType.of(prevPiece.get().pieceToString()).isKnight() &&
                isBlocking(prev, next)) {
            return false;
        }

        Optional<Piece> nextPiece = points.get(next);
        if (nextPiece.isPresent() &&
                PieceType.of(prevPiece.get().pieceToString()).isPawn() &&
                DirectionType.linearDirection().contains(DirectionType.valueOf(prev, next))) {
            return false;
        }

        if (nextPiece.isPresent() &&
                prevPiece.get().isSamePlayerType(nextPiece.get())) {
            return false;
        }
        return true;
    }

    private boolean isBlocking(Point prev, Point next) {
        int size = next.calculateMaxAbsoluteDistance(prev);
        DirectionType direction = DirectionType.valueOf(prev, next);

        return IntStream.range(1, size - 1)
                .mapToObj(i -> prev.moveOneStep(direction, i))
                .anyMatch(movedPoint -> points.get(movedPoint).isPresent());
    }


    public boolean isOwnPiece(Point prev, PlayerType playerType) {
        return points.get(prev)
                .orElseThrow(IllegalArgumentException::new)
                .isSamePlayerType(playerType);
    }

    public boolean isKingDead() {
        return isKingDead;
    }

    public double calculateScore(PlayerType playerType) {
        double score = 0;
        for (int i = 0; i < BOUNDARY; i++) {
            int row = i;
            Map<PieceType, Long> columnScore = IntStream.range(0, BOUNDARY)
                    .mapToObj(column -> Point.of(row, column))
                    .map(points::get)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(piece -> piece.isSamePlayerType(playerType))
                    .map(Piece::pieceToString)
                    .map(PieceType::of)
                    .collect(groupingBy(identity(),
                            () -> new EnumMap<>(PieceType.class),
                            counting()));

            score += columnScore.keySet().stream()
                    .mapToDouble(key -> key.calculateScore(columnScore.get(key)))
                    .sum();
        }

        return score;
    }

    public List<String> mappingBoardToString() {
        return IntStream.range(0, BOUNDARY)
                .mapToObj(column -> IntStream.range(0, BOUNDARY)
                        .mapToObj(row -> Point.of(row, column))
                        .map(points::get)
                        .map(this::pieceToString)
                        .collect(joining()))
                .collect(toList());
    }

    private String pieceToString(Optional<Piece> piece) {
        return piece.isPresent() ? piece.get().pieceToString() : EMPTY;
    }
}
