package chess.domain;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.Pawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Score {

    private final double whiteScore;
    private final double blackScore;

    public Score(Map<Position, ChessPiece> pieceByPosition) {
        final Map<Color, Double> scoreByColor = calculateScore(pieceByPosition);
        this.whiteScore = scoreByColor.getOrDefault(Color.WHITE, 0.0);
        this.blackScore = scoreByColor.getOrDefault(Color.BLACK, 0.0);
    }

    private Map<Color, Double> calculateScore(final Map<Position, ChessPiece> pieceByPosition) {
        return Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        color -> sumScoreExceptPawn(color, pieceByPosition) + sumPawnScore(color, pieceByPosition)));
    }

    private double sumScoreExceptPawn(final Color color, final Map<Position, ChessPiece> pieceByPosition) {
        return pieceByPosition.values().stream()
                .filter(chessPiece -> chessPiece.isSameColor(color))
                .filter(chessPiece -> !chessPiece.isPawn())
                .mapToDouble(ChessPiece::value)
                .sum();
    }

    private double sumPawnScore(final Color color, final Map<Position, ChessPiece> pieceByPosition) {
        return Arrays.stream(File.values())
                .mapToInt(rank -> countSameRankPawn(color, rank, pieceByPosition))
                .mapToDouble(Pawn::calculateScore)
                .sum();
    }

    private int countSameRankPawn(final Color color, final File file, final Map<Position, ChessPiece> pieceByPosition) {
        return (int) Arrays.stream(Rank.values())
                .map(rank -> pieceByPosition.get(Position.of(file, rank)))
                .filter(Objects::nonNull)
                .filter(ChessPiece::isPawn)
                .filter(pawn -> pawn.isSameColor(color))
                .count();
    }

    public double findScore(Color color) {
        if (color.isBlack()) {
            return blackScore;
        }
        return whiteScore;
    }
}
