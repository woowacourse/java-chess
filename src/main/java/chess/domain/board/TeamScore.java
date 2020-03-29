package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TeamScore {

    private static final double PAWN_SAME_FILE_SCORE = -0.5;
    private final Map<Color, Double> teamScore;

    public TeamScore(Collection<Piece> chessBoard,
        Map<Color, Integer> pawnSameFileByColor) {
        Objects.requireNonNull(chessBoard, "적합하지 않은 인자입니다.");
        Objects.requireNonNull(pawnSameFileByColor, "적합하지 않은 인자입니다.");
        this.teamScore = Collections.unmodifiableMap(getTeamScore(chessBoard, pawnSameFileByColor));
    }

    private Map<Color, Double> getTeamScore(Collection<Piece> chessBoard,
        Map<Color, Integer> pawnSameFileByColor) {
        Map<Color, Double> teamScore = new HashMap<>();
        for (Color color : Color.values()) {
            double colorScore = getSumScore(chessBoard, color)
                + (pawnSameFileByColor.get(color) * PAWN_SAME_FILE_SCORE);
            teamScore.put(color, colorScore);
        }
        return teamScore;
    }

    private double getSumScore(Collection<Piece> chessBoard, Color color) {
        return chessBoard.stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(Piece::getScore)
            .sum();
    }

    public List<Color> getWinners() {
        double winningScore = teamScore.values().stream()
            .max(Double::compareTo)
            .orElseThrow(IllegalAccessError::new);
        return teamScore.keySet().stream()
            .filter(color -> teamScore.get(color) == winningScore)
            .collect(Collectors.toList());
    }

    public Map<Color, Double> getTeamScore() {
        return teamScore;
    }
}
