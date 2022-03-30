package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.EnumMap;
import java.util.Map;

public class Score {
    private static final int existPawnNeighbors = 1;
    private static final int plusPawnNeighborsCount = 1;
    private static final int pawnNeighborsDefaultCount = 0;
    private static final double minusPawnNeighbors = 0.5;

    private double totalScore;

    public Score(Map<Position, Piece> board, Team team) {
        createTotalScore(board, team);
    }

    private void createTotalScore(Map<Position, Piece> board, Team team) {
        Map<Column, Integer> pawnNeighbors = new EnumMap<>(Column.class);
        for (Row row : Row.values()) {
            totalScore += calculateTotalScore(board, row, team);
            addPawnNeighbors(board, team, pawnNeighbors, row);
        }
        calculatePawnNeighbors(pawnNeighbors);
    }

    private void calculatePawnNeighbors(Map<Column, Integer> pawnNeighbors) {
        for (Column col : pawnNeighbors.keySet()) {
            minusPawnNeighborsScore(pawnNeighbors, col);
        }
    }

    private void minusPawnNeighborsScore(Map<Column, Integer> pawnNeighbors, Column col) {
        int pawnCount = pawnNeighbors.get(col);
        if (pawnCount > existPawnNeighbors) {
            totalScore -= pawnCount * minusPawnNeighbors;
        }
    }

    private void addPawnNeighbors(Map<Position, Piece> board, Team team, Map<Column, Integer> pawnNeighbors, Row row) {
        for (Column col : Column.values()) {
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

    private double calculateTotalScore(Map<Position, Piece> board, Row row, Team team) {
        double totalScore = 0;
        for (Column col : Column.values()) {
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

    public double getTotalScore() {
        return totalScore;
    }
}
