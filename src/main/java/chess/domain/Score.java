package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Score {
    private static final int EXIST_PAWN_NEIGHBORS = 1;
    private static final int PLUS_PAWN_NEIGHBORS_COUNT = 1;
    private static final int PAWN_NEIGHBORS_DEFAULT_COUNT = 0;
    private static final float MINUS_PAWN_NEIGHBORS_SCORE = 0.5f;

    private final Map<Team, Float> scoreBoard = new HashMap<>();

    public Score(Map<Position, Piece> board) {
        scoreBoard.put(Team.WHITE, createTotalScore(board, Team.WHITE));
        scoreBoard.put(Team.BLACK, createTotalScore(board, Team.BLACK));
    }

    private float createTotalScore(Map<Position, Piece> board, Team team) {
        float totalScore = 0;
        Map<Column, Integer> pawnNeighbors = new EnumMap<>(Column.class);
        for (Column col: Column.values()) {
            totalScore += calculateTotalScore(board, col, team);
            addPawnNeighbors(board, team, pawnNeighbors, col);
        }
        totalScore += getMinusPawnNeighborsScore(pawnNeighbors);
        return totalScore;
    }

    private float getMinusPawnNeighborsScore(Map<Column, Integer> pawnNeighbors) {
        float minusScore = 0;
        for (Column col : pawnNeighbors.keySet()) {
            minusScore += minusPawnNeighborsScore(pawnNeighbors, col);
        }
        return minusScore;
    }

    private float minusPawnNeighborsScore(Map<Column, Integer> pawnNeighbors, Column col) {
        int pawnCount = pawnNeighbors.get(col);
        float minusScore = 0;
        if (pawnCount > EXIST_PAWN_NEIGHBORS) {
            minusScore -= pawnCount * MINUS_PAWN_NEIGHBORS_SCORE;
        }
        return minusScore;
    }

    private void addPawnNeighbors(Map<Position, Piece> board, Team team, Map<Column, Integer> pawnNeighbors, Column col) {
        for (Row row : Row.values()) {
            Piece piece = board.get(Position.of(col, row));
            addCountPawnNeighbors(team, pawnNeighbors, col, piece);
        }
    }

    private void addCountPawnNeighbors(Team team, Map<Column, Integer> pawnNeighbors, Column col, Piece piece) {
        if (piece.isPawn() && team.matchTeam(piece.getTeam())) {
            pawnNeighbors.put(col, pawnNeighbors.getOrDefault(col, PAWN_NEIGHBORS_DEFAULT_COUNT) + PLUS_PAWN_NEIGHBORS_COUNT);
        }
    }

    private float calculateTotalScore(Map<Position, Piece> board, Column col, Team team) {
        float totalScore = 0;
        for (Row row : Row.values()) {
            Piece piece = board.get(Position.of(col, row));
            totalScore = plusPieceScore(team, totalScore, piece);
        }
        return totalScore;
    }

    private float plusPieceScore(Team team, float totalScore, Piece piece) {
        if (piece.getTeam().matchTeam(team)) {
            totalScore += piece.getScore();
        }
        return totalScore;
    }

    public float getTotalScoreWhiteTeam() {
        return scoreBoard.get(Team.WHITE);
    }

    public float getTotalScoreBlackTeam() {
        return scoreBoard.get(Team.BLACK);
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
