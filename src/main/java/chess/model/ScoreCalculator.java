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

    public double currentPlayerScore() {
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
            .filter(rank -> piecesByPositions.containsKey(Position.of(rank, file)))
            .filter(rank -> pieceAt(Position.of(rank, file)).isPawn() &&
                turnDecider.isTurnOf(pieceAt(Position.of(rank, file))))
            .count();
    }

    private Piece pieceAt(Position position) {
        return piecesByPositions.get(position);
    }
}
