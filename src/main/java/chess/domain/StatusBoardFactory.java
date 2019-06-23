package chess.domain;

import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StatusBoardFactory {
    public static StatusBoard create(Board board) {
        double blackScore = getTeamScore(board, Team.BLACK);
        double whiteScore = getTeamScore(board, Team.WHITE);
        return new StatusBoard(blackScore, whiteScore);
    }

    private static double getTeamScore(Board board, Team team) {
        Map<Spot, Piece> pieces = board.getPieces(team);
        double score = getScoreExceptPawn(pieces);

        List<Spot> pawnSpots = new ArrayList<>();
        pieces.forEach((k, v) -> {
            if (v.score() == 1) {
                pawnSpots.add(k);
            }
        });
        score += getPawnScore(pawnSpots);
        return score;
    }

    private static double getScoreExceptPawn(Map<Spot, Piece> pieces) {
        return pieces.values().stream()
                .filter(x -> x.score() != 1)
                .mapToDouble(x -> x.score())
                .sum();
    }

    private static double getPawnScore(List<Spot> pawnSpots) {
        Collections.sort(pawnSpots, Spot::getX);

        List<Integer> results = new ArrayList<>();

        if (pawnSpots.size() == 0) {
            return 0;
        }

        int count = 1;
        for (int i = 1; i < pawnSpots.size(); i++) {
            if (pawnSpots.get(i).isSameRaw(pawnSpots.get(i - 1))) {
                count++;
            } else {
                results.add(count);
                count = 1;
            }
        }
        results.add(count);

        double totalResult = 0;
        for (Integer result : results) {
            totalResult += calculateNumber(result);
        }
        return totalResult;
    }

    private static double calculateNumber(int number) {
        if (number <= 1) {
            return number;
        }
        return number * 0.5;
    }
}
