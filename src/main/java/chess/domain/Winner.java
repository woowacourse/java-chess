package chess.domain;

import chess.domain.board.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Winner {

    public static List<Color> getWinners(Map<Square, Piece> chessBoard) {
        Map<Color, Double> teamScores = TeamScore.calculateTeamScore(chessBoard);
        return analyzeWinner(teamScores);
    }

    private static List<Color> analyzeWinner(Map<Color, Double> teamScores) {
        List<Color> winner = new ArrayList<>();
        winner.add(Color.BLACK);
        winner.add(Color.WHITE);
        removeLoser(teamScores, winner);
        return winner;
    }

    private static void removeLoser(Map<Color, Double> teamScores, List<Color> winner) {
        if (teamScores.get(Color.BLACK) > teamScores.get(Color.WHITE)) {
            winner.remove(Color.WHITE);
        }
        if (teamScores.get(Color.BLACK) < teamScores.get(Color.WHITE)) {
            winner.remove(Color.BLACK);
        }
    }
}
