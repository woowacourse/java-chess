package chess.model;

import static chess.model.Rank.*;

import java.util.Arrays;
import java.util.Map;

import chess.model.piece.Piece;

public class ScoreCalculator {

    private final Map<Position, Piece> piecesByPositions;
    private final TurnDecider turnDecider;

    public ScoreCalculator(Map<Position, Piece> piecesByPositions, TurnDecider turnDecider) {
        this.piecesByPositions = piecesByPositions;
        this.turnDecider = turnDecider;
    }

    public double calculate() {
        return piecesByPositions.values()
            .stream()
            .filter(turnDecider::isTurnOf)
            .mapToDouble(Piece::getScore)
            .sum() - adjustPawnScore();
    }

    public double adjustPawnScore() {
        return Arrays.stream(File.values())
            .map(this::getPawnCountInOneFile)
            .filter(count -> count > 1)
            .mapToDouble(count -> count * 0.5)
            .sum();

    }

    private long getPawnCountInOneFile(File file) {
        return reverseValues().stream()
            .map(rank -> Position.of(rank, file))
            .filter(position -> pieceAt(position).isPawn() && turnDecider.isTurnOf(pieceAt(position)))
            .count();
    }

    private Piece pieceAt(Position position) {
        return piecesByPositions.get(position);
    }
}
