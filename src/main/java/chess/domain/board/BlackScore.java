package chess.domain.board;

import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BlackScore implements Score {

    private static final double DECREASE_SCORE_BY_DISTINCT = (-0.5);
    private static final int PIECE_HAVE_ONE_MORE_THAN_DUPLICATION_FILE = 1;
    private final double score;

    public BlackScore(Map<Position, Piece> pieces) {
        score = calculateScoreOf(getPieceEntryOf(pieces, Color.BLACK));
    }

    private List<Map.Entry<Position, Piece>> getPieceEntryOf(Map<Position, Piece> pieces, Color color) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().getColor() == color)
                .collect(Collectors.toList());
    }

    private double calculateScoreOf(List<Map.Entry<Position, Piece>> pieces) {
        if (dontHaveKing(pieces)) {
            return 0;
        }
        double result = 0;
        result += calculateScoreExceptPawn(pieces);
        result += calculatePawnScore(pieces);
        return result;
    }

    private boolean dontHaveKing(List<Map.Entry<Position, Piece>> pieces) {
        return !pieces.stream()
                .anyMatch(entry -> entry.getValue().isTypeOf(PieceType.KING));
    }

    private double calculateScoreExceptPawn(List<Map.Entry<Position, Piece>> pieces) {
        List<Map.Entry<Position, Piece>> piecesExceptPawn = findEntryOf(pieces,
                (entry) -> !entry.getValue().isTypeOf(PieceType.PAWN) && !entry.getValue().isTypeOf(PieceType.INIT_PAWN)
        );
        return calculateScoreSumOf(piecesExceptPawn);
    }

    private double calculateScoreSumOf(List<Map.Entry<Position, Piece>> piecesExceptPawn) {
        return piecesExceptPawn.stream()
                .mapToDouble(entry -> entry.getValue().getScore())
                .sum();
    }

    private List<Map.Entry<Position, Piece>> findEntryOf(List<Map.Entry<Position, Piece>> pieces, Predicate<Map.Entry<Position, Piece>> predicate) {
        return pieces.stream()
                .filter((entry) -> predicate.test(entry))
                .collect(Collectors.toList());

    }

    private double calculatePawnScore(List<Map.Entry<Position, Piece>> pieces) {
        List<Map.Entry<Position, Piece>> pawnPieces = findEntryOf(pieces,
                (entry) -> entry.getValue().isTypeOf(PieceType.PAWN) || entry.getValue().isTypeOf(PieceType.INIT_PAWN)
        );
        double origin = calculateScoreSumOf(pawnPieces);
        return origin + calculateDecreaseScoreOfPawn(pawnPieces);
    }

    private double calculateDecreaseScoreOfPawn(List<Map.Entry<Position, Piece>> pieces) {
        long distinctFilePositionSize = pieces.stream().map(entry -> entry.getKey().getFile())
                .distinct()
                .count();

        if (distinctFilePositionSize != pieces.size()) {
            long distinctPieceCount = pieces.size() - distinctFilePositionSize + PIECE_HAVE_ONE_MORE_THAN_DUPLICATION_FILE;
            return distinctPieceCount * DECREASE_SCORE_BY_DISTINCT;
        }

        return 0;
    }

    @Override
    public double getScore() {
        return score;
    }
}
