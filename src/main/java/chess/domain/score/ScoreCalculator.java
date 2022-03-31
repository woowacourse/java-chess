package chess.domain.score;

import chess.domain.ChessScore;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScoreCalculator {

    private static Map<Integer, List<Piece>> generateSameColumnCache(Map<Position, Piece> pieces,
                                                              Predicate<Piece> piecePredicate) {
        Map<Integer, List<Piece>> columnCache = new HashMap<>();
        for (int nowColumn = Position.MIN; nowColumn <= Position.MAX; nowColumn++) {
            columnCache.put(nowColumn, groupingByColumn(pieces, piecePredicate, nowColumn));
        }
        return columnCache;
    }

    private static List<Piece> groupingByColumn(Map<Position, Piece> pieces, Predicate<Piece> piecePredicate, int nowColumn) {
        return pieces.keySet().stream()
                .filter(position -> position.isSameColumn(nowColumn))
                .filter(position -> piecePredicate.test(pieces.get(position)))
                .map(pieces::get)
                .collect(Collectors.toList());
    }

    private static double generateScore(Map<Position, Piece> pieces, Predicate<Piece> piecePredicate) {
        Map<Integer, List<Piece>> columnCache = generateSameColumnCache(pieces,
                piecePredicate);
        return columnCache.values().stream()
                .mapToDouble(columnPieces -> Score.from(columnPieces) * columnPieces.size())
                .sum();
    }

    public static ChessScore calculateChessScore(Map<Position, Piece> pieces) {
        return new ChessScore(
                generateScore(pieces, (piece) -> piece.isPawn() && piece.isWhite()) + generateScore(pieces,
                        (piece) -> !piece.isPawn() && piece.isWhite()),
                generateScore(pieces, (piece) -> piece.isPawn() && !piece.isWhite()) + generateScore(pieces,
                        (piece) -> !piece.isPawn() && !piece.isWhite())
        );
    }
}
