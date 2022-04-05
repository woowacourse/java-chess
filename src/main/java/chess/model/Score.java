package chess.model;

import static chess.model.Rank.*;

import java.util.Arrays;
import java.util.Map;

import chess.model.piece.Piece;

public class Score {

    public static final double PAWN_SCORE_FACTOR = 0.5;

    private final Map<Position, Piece> piecesByPositions;
    private final Turn turn;

    Score(Map<Position, Piece> piecesByPositions, Turn turn) {
        this.piecesByPositions = piecesByPositions;
        this.turn = turn;
    }

    double calculate() {
        return piecesByPositions.values()
            .stream()
            .filter(turn::isTurnOf)
            .mapToDouble(Piece::getScore)
            .sum() - adjustPawnScore();
    }

    private double adjustPawnScore() {
        return Arrays.stream(File.values())
            .map(this::getPawnCountInOneFile)
            .filter(this::isPawnInLine)
            .mapToDouble(count -> count * PAWN_SCORE_FACTOR)
            .sum();

    }

    private boolean isPawnInLine(Long count) {
        return count > 1;
    }

    private long getPawnCountInOneFile(File file) {
        return reverseValues().stream()
            .filter(rank -> piecesByPositions.containsKey(Position.of(file, rank)))
            .filter(rank -> pieceAt(Position.of(file, rank)).isPawn() &&
                turn.isTurnOf(pieceAt(Position.of(file, rank))))
            .count();
    }

    private Piece pieceAt(Position position) {
        return piecesByPositions.get(position);
    }
}
