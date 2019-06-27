package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatusBoardFactory {
    public static StatusBoard create(Board board) {
        double blackScore = getTeamScore(board, Team.BLACK);
        double whiteScore = getTeamScore(board, Team.WHITE);
        return new StatusBoard(blackScore, whiteScore);
    }

    private static double getTeamScore(Board board, Team team) {
        Map<Spot, Piece> pieces = board.getTeamPieces(team);
        double score = getScoreExceptPawn(pieces);
        List<Spot> pawnSpots = getPawnSpots(pieces);

        score += getPawnScore(pawnSpots);
        return score;
    }

    private static double getScoreExceptPawn(Map<Spot, Piece> pieces) {
        return pieces.values().stream()
                .filter(piece -> piece.score() != Pawn.PAWN_SCORE)
                .mapToDouble(Piece::score)
                .sum();
    }

    private static List<Spot> getPawnSpots(Map<Spot, Piece> pieces) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().score() == Pawn.PAWN_SCORE)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static double getPawnScore(List<Spot> pawnSpots) {
        if (pawnSpots.isEmpty()) {
            return 0;
        }
        sortPawnByXSpot(pawnSpots);
        List<Integer> sameLinePawnCounts = getSameLinePawnCounts(pawnSpots);

        double totalResult = 0;
        for (Integer result : sameLinePawnCounts) {
            totalResult += calculatePawnScore(result);
        }
        return totalResult;
    }

    private static void sortPawnByXSpot(List<Spot> pawnSpots) {
        pawnSpots.sort(Spot::xGap);
    }

    private static List<Integer> getSameLinePawnCounts(List<Spot> pawnSpots) {
        List<Integer> results = new ArrayList<>();
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
        return results;
    }

    private static double calculatePawnScore(int number) {
        if (number <= 1) {
            return number;
        }
        return number * 0.5;
    }
}
