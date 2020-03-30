package chess.domain;

import chess.domain.board.Square;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeamScore {

    public static Map<Color, Double> calculateTeamScore(Map<Square, Piece> chessBoard) {
        Map<Color, Double> teamScore = new HashMap<>();
        double blackScore = chessBoard.values().stream()
                .filter(piece -> piece.getColor().equals(Color.BLACK))
                .mapToDouble(Piece::getScore).sum();
        double whiteScore = chessBoard.values().stream()
                .filter(piece -> piece.getColor().equals(Color.WHITE))
                .mapToDouble(Piece::getScore).sum();
        blackScore -= calculatePawnScore(blackScore, Color.BLACK, chessBoard);
        whiteScore -= calculatePawnScore(whiteScore, Color.WHITE, chessBoard);
        setTeamScore(teamScore, blackScore, whiteScore);
        return teamScore;
    }

    private static void setTeamScore(Map<Color, Double> teamScore, Double blackScore, Double whiteScore) {
        teamScore.put(Color.BLACK, blackScore);
        teamScore.put(Color.WHITE, whiteScore);
    }

    private static double calculatePawnScore(Double score, Color color, Map<Square, Piece> chessBoard) {
        List<Square> pawns = chessBoard.keySet().stream()
                .filter(square -> chessBoard.get(square) == Pawn.of(color))
                .collect(Collectors.toList());
        int count = 0;
        for (Square pawn : pawns) {
            count += pawns.stream()
                    .filter(pawnToCompare -> pawn.getFile() == pawnToCompare.getFile() && !pawn.equals(pawnToCompare))
                    .count();
        }
        return count * Pawn.REDUCED_PAWN_SCORE;
    }

    public static List<Color> getWinners(Map<Square, Piece> chessBoard) {
        Map<Color, Double> teamScores = calculateTeamScore(chessBoard);
        return analyzeWinner(teamScores);
    }

    private static List<Color> analyzeWinner(Map<Color, Double> teamScores) {
        List<Color> winner = new ArrayList<>();
        winner.add(Color.BLACK);
        winner.add(Color.WHITE);
        if (teamScores.get(Color.BLACK) > teamScores.get(Color.WHITE)) {
            winner.remove(Color.WHITE);
        }
        if (teamScores.get(Color.BLACK) < teamScores.get(Color.WHITE)) {
            winner.remove(Color.BLACK);
        }
        return winner;
    }
}
