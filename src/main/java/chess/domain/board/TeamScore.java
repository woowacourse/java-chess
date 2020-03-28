package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TeamScore {

    private static final double PAWN_SAME_FILE_SCORE = -0.5;
    private final Map<Color, Double> teamScore;

    public TeamScore(Map<BoardSquare, Piece> chessBoard) {
        Objects.requireNonNull(chessBoard, "적합하지 않은 인자입니다.");
        this.teamScore = Collections.unmodifiableMap(getTeamScore(chessBoard));
    }

    private Map<Color, Double> getTeamScore(Map<BoardSquare, Piece> chessBoard) {
        Map<Color, Double> teamScore = new HashMap<>();
        for (Color color : Color.values()) {
            double colorScore = getSumScore(chessBoard, color);
            teamScore.put(color, colorScore);
        }
        return teamScore;
    }

    private double getSumScore(Map<BoardSquare, Piece> chessBoard, Color color) {
        return chessBoard.values().stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(Piece::getScore)
            .sum()
            + chargePawnSameFileScore(chessBoard, color);
    }

    private double chargePawnSameFileScore(Map<BoardSquare, Piece> chessBoard, Color color) {
        int count;
        List<BoardSquare> pawnSquare = chessBoard.keySet().stream()
            .filter(square -> chessBoard.get(square) == Pawn.getPieceInstance(color))
            .collect(Collectors.toList());
        count = 0;
        for (BoardSquare boardSquare : pawnSquare) {
            count += getSameFileCount(pawnSquare, boardSquare);
        }
        return count * PAWN_SAME_FILE_SCORE;
    }

    private long getSameFileCount(List<BoardSquare> pawnSquare, BoardSquare boardSquare) {
        return pawnSquare.stream()
            .filter(square -> boardSquare.isSameFile(square) && boardSquare != square)
            .count();
    }

    public List<Color> getWinners() {
        double WinningScore = teamScore.values().stream()
            .max(Double::compareTo)
            .orElseThrow(IllegalAccessError::new);
        return teamScore.keySet().stream()
            .filter(color -> teamScore.get(color) == WinningScore)
            .collect(Collectors.toList());
    }

    public Map<Color, Double> getTeamScore() {
        return teamScore;
    }
}
