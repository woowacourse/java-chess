package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import chess.domain.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class Board {

    private static final int TOTAL_PIECE_COUNT = LineNumber.MAX * LineNumber.MAX;
    private static final int TOTAL_KING_COUNT = 2;

    private final Map<Point, Piece> pointPieces;

    private Board(Map<Point, Piece> pointPieces) {
        validateCounts(pointPieces);
        this.pointPieces = new HashMap<>(pointPieces);
    }

    private void validateCounts(Map<Point, Piece> pointPieces) {
        if (pointPieces.size() != TOTAL_PIECE_COUNT) {
            throw new IllegalArgumentException(
                String.format("[ERROR] 말의 개수가 %d개가 아닙니다.", TOTAL_PIECE_COUNT)
            );
        }
    }

    public static Board of(BoardGenerator generator) {
        return new Board(generator.generate());
    }

    public Board move(Route route, Color turnColor) {
        validate(turnColor, route);
        tryMove(route);
        return replacePiecePoint(route);
    }

    private void validate(Color turnColor, Route route) {
        validateAllyMove(turnColor, pointPieces.get(route.getSource()));
        validateNotAllyAttack(turnColor, pointPieces.get(route.getDestination()));
    }

    private void validateAllyMove(Color turnColor, Piece fromPiece) {
        if (!fromPiece.isSameColor(turnColor)) {
            throw new IllegalStateException("[ERROR] 자신의 말을 움직여야 합니다.");
        }
    }

    private void validateNotAllyAttack(Color turnColor, Piece toPiece) {
        if (toPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("[ERROR] 아군을 공격할 수 없습니다.");
        }
    }

    private void tryMove(Route route) {
        Piece fromPiece = pointPieces.get(route.getSource());
        if (!fromPiece.move(route, EmptyPoints.of(pointPieces))) {
            throw new IllegalStateException("[ERROR] 해당 위치로 움직일 수 없습니다.");
        }
    }

    private Board replacePiecePoint(Route route) {
        Map<Point, Piece> newPointPieces = new HashMap<>(this.pointPieces);
        newPointPieces.put(route.getDestination(), newPointPieces.get(route.getSource()));
        newPointPieces.put(route.getSource(), Empty.getInstance());
        return new Board(newPointPieces);
    }

    public Map<Color, Double> calculateScore() {
        return Stream.of(Color.WHITE, Color.BLACK)
            .collect(toMap(
                Function.identity(),
                color -> PieceType.calculateScore(pointPieces, color)
            ));
    }

    public boolean isKingDead() {
        return pointPieces.values()
            .stream()
            .filter(piece -> piece.isSameType(PieceType.KING))
            .count() < TOTAL_KING_COUNT;
    }

    public Map<Point, Piece> getPointPieces() {
        return Map.copyOf(pointPieces);
    }
}
