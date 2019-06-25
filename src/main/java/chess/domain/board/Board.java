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

    // TODO: 2019-06-22 private!!
    public boolean canMove(Point prev, Point next) {
        Optional<Piece> prevPiece = points.get(prev);
        if (!prevPiece.isPresent()) {
            return false;
        }

        DirectionType direction = DirectionType.valueOf(prev, next);


        if (!PieceType.of(prevPiece.get().pieceToString()).isKnight()) {
            int size = next.calculateMaxAbsoluteDistance(prev);

//            IntStream.range(1, size - 1)
//                    .mapToObj(i -> prev.moveOneStep(direction, i))
//                    .anyMatch(movedPoint -> points.get(movedPoint).isPresent());

            for (int i = 1; i < size - 1; i++) {
                Point moving = prev.moveOneStep(direction, i);
                if (points.get(moving).isPresent()) {
                    return false;
                }
            }
        }

        Optional<Piece> nextPiece = points.get(next);
        if (nextPiece.isPresent() && PieceType.of(prevPiece.get().pieceToString()).isPawn()) {
            // TODO: 2019-06-22 대각선 -> 직선으로
            if (!DirectionType.diagonalDirection().contains(direction)) {
                return false;
            }
        }

        if (nextPiece.isPresent() && points.get(prev).get().isSamePlayerType(nextPiece.get())) {
            return false;
        }
        return true;
    }

    public void move(Point prev, Point next) {
        if (canMove(prev, next) && points.get(prev).get().isMovable(prev, next)) {
            Optional<Piece> nextPiece = points.get(next);
            if (nextPiece.isPresent() && PieceType.of(nextPiece.get().pieceToString()).isKing()) {
                isKingDead = true;
            }
            points.put(next, points.get(prev));
            points.put(prev, Optional.empty());
        }
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

    // TODO: 2019-06-23 Rename this method!!
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
