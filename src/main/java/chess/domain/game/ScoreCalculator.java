package chess.domain.game;

import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreCalculator {

    public static final double HALF_SCORE = 0.5;

    private final List<Rank> ranks;

    public ScoreCalculator(final List<Rank> ranks) {
        this.ranks = ranks;
    }

    public double totalWhiteScore() {
        return scoreExceptPawns(Color.WHITE) + scoreOfPawns(Color.WHITE);
    }

    public double totalBlackScore() {
        return scoreExceptPawns(Color.BLACK) + scoreOfPawns(Color.BLACK);
    }

    private double scoreOfPawns(Color color) {
        return Arrays.stream(Xpoint.values())
            .mapToDouble(xPoint -> verticalPawnScore(xPoint, color))
            .sum();
    }

    private double verticalPawnScore(Xpoint xpoint, Color color) {
        long countOfPawnsInVertical = countOfPawnsInVertical(xpoint, color);

        if (countOfPawnsInVertical > 1) {
            return countOfPawnsInVertical * HALF_SCORE;
        }

        return countOfPawnsInVertical;
    }

    private long countOfPawnsInVertical(Xpoint xpoint, Color color) {
        List<Position> verticalPositions = verticalPositions(xpoint);

        return this.ranks.stream()
            .map(Rank::squares)
            .map(Map::entrySet)
            .flatMap(Collection::stream)
            .filter(entry -> verticalPositions.contains(entry.getKey()))
            .map(Map.Entry::getValue)
            .filter(piece -> piece.isSameColor(color))
            .filter(Piece::isPawn)
            .count();
    }

    private List<Position> verticalPositions(Xpoint xpoint) {
        return this.ranks.stream()
            .map(Rank::squares)
            .map(Map::keySet)
            .flatMap(Collection::stream)
            .filter(position -> position.isSameX(xpoint))
            .collect(Collectors.toList());
    }

    private double scoreExceptPawns(Color color) {
        return ranks.stream()
            .map(Rank::squares)
            .map(Map::values)
            .flatMap(Collection::stream)
            .filter(piece -> piece.isSameColor(color))
            .filter(Piece::isNotPawn)
            .mapToDouble(Piece::score)
            .sum();
    }
}
