package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Score {
    private static final int existPawnNeighbors = 1;
    private static final int plusPawnNeighborsCount = 1;
    private static final int pawnNeighborsDefaultCount = 0;
    private static final double minusPawnNeighborsScore = 0.5;

    private Map<Team, Double> scoreBoard = new HashMap<>();

    public Score(Map<Position, Piece> board) {
        scoreBoard.put(Team.WHITE, createTotalScore(board, Team.WHITE));
        scoreBoard.put(Team.BLACK, createTotalScore(board, Team.BLACK));
    }

    private double createTotalScore(Map<Position, Piece> board, Team team) {
        double totalScore = 0;
        Map<Column, Integer> pawnNeighbors = new EnumMap<>(Column.class);
        for (Column col: Column.values()) {
            totalScore += calculateTotalScore(board, col, team);
            addPawnNeighbors(board, team, pawnNeighbors, col);
        }
        totalScore += getMinusPawnNeighborsScore(pawnNeighbors);
        return totalScore;
    }

    private double getMinusPawnNeighborsScore(Map<Column, Integer> pawnNeighbors) {
        double minusScore = 0;
        for (Column col : pawnNeighbors.keySet()) {
            minusScore += minusPawnNeighborsScore(pawnNeighbors, col);
        }
        return minusScore;
    }

    private double minusPawnNeighborsScore(Map<Column, Integer> pawnNeighbors, Column col) {
        int pawnCount = pawnNeighbors.get(col);
        double minusScore = 0;
        if (pawnCount > existPawnNeighbors) {
            minusScore -= pawnCount * minusPawnNeighborsScore;
        }
        return minusScore;
    }

    private void addPawnNeighbors(Map<Position, Piece> board, Team team, Map<Column, Integer> pawnNeighbors, Column col) {
        for (Row row : Row.values()) {
            String position =  col.getSymbol() + row.getSymbol();
            Piece piece = board.get(Position.from(position));
            addCountPawnNeighbors(team, pawnNeighbors, col, piece);
        }
    }

    private void addCountPawnNeighbors(Team team, Map<Column, Integer> pawnNeighbors, Column col, Piece piece) {
        if (piece.isPawn() && team.matchTeam(piece.getTeam())) {
            pawnNeighbors.put(col, pawnNeighbors.getOrDefault(col, pawnNeighborsDefaultCount) + plusPawnNeighborsCount);
        }
    }

    private double calculateTotalScore(Map<Position, Piece> board, Column col, Team team) {
        double totalScore = 0;
        for (Row row : Row.values()) {
            String position = col.getSymbol() + row.getSymbol();
            Piece piece = board.get(Position.from(position));
            totalScore = plusPieceScore(team, totalScore, piece);
        }
        return totalScore;
    }

    private double plusPieceScore(Team team, double totalScore, Piece piece) {
        if (piece.getTeam().matchTeam(team)) {
            totalScore += piece.getScore();
        }
        return totalScore;
    }

    public double getTotalScoreWhiteTeam() {
        return scoreBoard.get(Team.WHITE);
    }

    public double getTotalScoreBlackTeam() {
        return scoreBoard.get(Team.WHITE);
    }

    public Team getWinningTeam() {
        if (scoreBoard.get(Team.BLACK) > scoreBoard.get(Team.WHITE)) {
            return Team.BLACK;
        }
        if (scoreBoard.get(Team.BLACK) < scoreBoard.get(Team.WHITE)) {
            return Team.WHITE;
        }
        return Team.NONE;
    }
}
