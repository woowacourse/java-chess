package chess.domain.state;

import chess.domain.piece.Color;
import chess.domain.piece.Notation;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public abstract class CalculableState extends State {

    private static final int MIN_PAWN_COUNT = 1;
    private static final int NUMBER_DIVIDE_PAWN_SCORE = 2;

    @Override
    public final double score(final Color color) {
        return board.getValue().entrySet().stream()
                .filter(e -> e.getValue().isSameColor(color))
                .mapToDouble(this::getScore)
                .sum();
    }

    private double getScore(final Map.Entry<Position, Piece> entry) {
        final Piece piece = entry.getValue();

        if (piece.getNotation() == Notation.PAWN) {
            return calculatePawnScore(entry);
        }
        return piece.getScore();
    }

    private double calculatePawnScore(final Map.Entry<Position, Piece> entry) {
        final Position position = entry.getKey();
        final Piece pawn = entry.getValue();

        long pawnCountInSameFile = board.getValue().entrySet().stream()
                .filter(e -> e.getValue().equals(pawn))
                .filter(e -> e.getKey().isSameFile(position.getFile()))
                .count();

        if (pawnCountInSameFile > MIN_PAWN_COUNT) {
            return pawn.getScore() / NUMBER_DIVIDE_PAWN_SCORE;
        }
        return pawn.getScore();
    }
}
