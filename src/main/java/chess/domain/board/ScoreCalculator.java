package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScoreCalculator {

    private static final double HALF_PAWN_SCORE = 0.5;

    public static double calculateScoreOfBlack(Map<Position, Piece> value) {
        return Arrays.stream(Column.values())
                .map(column -> collectBlackPiecesIn(column, value))
                .mapToDouble(pieces -> calculatePawnScoreIn(pieces) + calculateScoreWithoutPawnIn(pieces))
                .sum();
    }

    private static List<Piece> collectBlackPiecesIn(Column column, Map<Position, Piece> value) {
        return Arrays.stream(Row.values())
                .map(row -> value.get(Position.of(column, row)))
                .filter(Objects::nonNull)
                .filter(Piece::isBlack)
                .collect(Collectors.toList());
    }

    public static double calculateScoreOfWhite(Map<Position, Piece> value) {
        return Arrays.stream(Column.values())
                .map(column -> collectWhitePiecesIn(column, value))
                .mapToDouble(pieces -> calculatePawnScoreIn(pieces) + calculateScoreWithoutPawnIn(pieces))
                .sum();
    }

    private static List<Piece> collectWhitePiecesIn(Column column, Map<Position, Piece> value) {
        return Arrays.stream(Row.values())
                .map(row -> value.get(Position.of(column, row)))
                .filter(Objects::nonNull)
                .filter(piece -> !piece.isBlack())
                .collect(Collectors.toList());
    }

    private static double calculateScoreWithoutPawnIn(List<Piece> pieces) {
        List<Piece> piecesWithoutPawn = collectPiecesWithoutPawnIn(pieces);
        return piecesWithoutPawn.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private static List<Piece> collectPiecesWithoutPawnIn(List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> !piece.isPawn())
                .collect(Collectors.toList());
    }

    private static double calculatePawnScoreIn(List<Piece> pieces) {
        List<Piece> pawns = collectPawnsIn(pieces);
        if (pawns.size() == 0) {
            return 0;
        }
        if (pawns.size() > 1) {
            return HALF_PAWN_SCORE * pawns.size();
        }
        return pawns.get(0).getScore() * pawns.size();
    }

    private static List<Piece> collectPawnsIn(List<Piece> pieces) {
        return pieces.stream()
                .filter(Piece::isPawn)
                .collect(Collectors.toList());
    }
}
