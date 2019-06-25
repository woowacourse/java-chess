package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

public class ChessScoreCount {
    private Map<Team, Double> scores;

    public ChessScoreCount(Board board) {
        scores = new HashMap<>();
        scores.put(Team.BLACK, calculateScoreByTeam(board, Team.BLACK));
        scores.put(Team.WHITE, calculateScoreByTeam(board, Team.WHITE));
    }

    private double calculateScoreByTeam(Board board, Team team) {
        double result = 0;
        List<ChessXCoordinate> xAxis = ChessXCoordinate.allAscendingCoordinates();

        for (ChessXCoordinate xAxi : xAxis) {
            List<PieceType> pieces = getPieceTypesByXcoord(board, team, xAxi);

            if (hasPawns(pieces, team)) {
                result += sumPieceTypeScoreBypieces(pieces);
                continue;
            }
            result += sumPieceTypeScore(pieces);
        }

        return result;
    }

    private double sumPieceTypeScore(List<PieceType> pieces) {
        double result = 0;
        for (PieceType piece : pieces) {
            result += piece.getScore();
        }
        return result;
    }

    private double sumPieceTypeScoreBypieces(List<PieceType> pieces) {
        double result = 0;
        for (PieceType piece : pieces) {
            if (piece == PieceType.PAWN_BLACK || piece == PieceType.PAWN_WHITE) {
                result += 0.5;
                continue;
            }
            result += piece.getScore();
        }
        return result;
    }

    private List<PieceType> getPieceTypesByXcoord(Board board, Team team, ChessXCoordinate xAxi) {
        return board.getBoardState().entrySet().stream()
                        .filter(entry -> entry.getKey().getX() == xAxi)
                        .filter(entry -> entry.getValue().getTeam() == team)
                        .map(Map.Entry::getValue)
                        .collect(Collectors.toList());
    }

    private boolean hasPawns(List<PieceType> pieceTypes, Team team) {
        if (team == Team.BLACK) {
            return pieceTypes.stream().filter(p -> p.equals(PieceType.PAWN_BLACK)).count() > 1;
        }
        return pieceTypes.stream().filter(p -> p.equals(PieceType.PAWN_WHITE)).count() > 1;
    }

    public double getScore(Team team) {
        return scores.get(team);
    }
}
