package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoard {
    private static final double MULTIPLE_PAWN_POINT = 0.5;
    private static final double MULTIPLE_PAWN_COUNT = 2;

    private final Map<Color, Double> score;

    public ScoreBoard(Map<Color, Double> score) {
        this.score = score;
    }

    public static ScoreBoard make(Map<Position, Piece> board) {
        Map<Color, Double> score= new HashMap<>();
        double whiteTeamScore = pieceScore(Color.WHITE, board) - multiplePawnScore(Color.WHITE, board);
        double blackTeamScore = pieceScore(Color.BLACK, board) - multiplePawnScore(Color.BLACK, board);

        score.put(Color.WHITE, whiteTeamScore);
        score.put(Color.BLACK, blackTeamScore);

        return new ScoreBoard(score);
    }

    private static double pieceScore(Color color, Map<Position, Piece> board) {
        return board.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(piece -> piece.getType().getPoint())
                .sum();
    }

    private static double multiplePawnScore(Color color, Map<Position, Piece> board) {
        final Map<File, Long> pawnCount = Arrays.stream(Rank.values())
                .flatMap(file -> Arrays.stream(File.values()).map(rank -> Position.from(rank, file)))
                .filter(position -> board.get(position).isSameColor(color))
                .filter(position -> board.get(position).isSameType(PieceType.PAWN))
                .collect(Collectors.groupingBy(Position::getFile, Collectors.counting()));

        return pawnCount.values().stream()
                .filter(value -> value >= MULTIPLE_PAWN_COUNT)
                .mapToDouble(value -> value * MULTIPLE_PAWN_POINT)
                .sum();
    }

    public Map<Color, Double> getScore() {
        return Collections.unmodifiableMap(score);
    }
}
