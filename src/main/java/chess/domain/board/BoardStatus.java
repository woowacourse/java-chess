package chess.domain.board;

import chess.domain.piece.Team;

import java.util.Map;

public class BoardStatus {
    private static final double TOTAL_SCORE = 38;
    private static final double PAWN_SAME_HORIZONTAL_SCORE = 0.5;

    private Team lastTurn;
    private Score blackScore;
    private Score whiteScore;

    public BoardStatus() {
        this.lastTurn = Team.BLACK;
        blackScore = new Score(TOTAL_SCORE);
        whiteScore = new Score(TOTAL_SCORE);
    }

    public void nextTurn() {
        lastTurn = changeTurn();
    }

    private Team changeTurn() {
        if (isWhiteTurn()) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public void calculateScore(Map<Team, Double> deadPieceByTeam
            , Long countOfSameLinePawnBlack, Long countOfSameLinePawnWhite) {
        double blackScore = TOTAL_SCORE - deadPieceByTeam.get(Team.BLACK)
                - countOfSameLinePawnBlack * PAWN_SAME_HORIZONTAL_SCORE;
        this.blackScore = new Score(blackScore);
        double whiteScore = TOTAL_SCORE - deadPieceByTeam.get(Team.WHITE)
                - countOfSameLinePawnWhite * PAWN_SAME_HORIZONTAL_SCORE;
        this.whiteScore = new Score(whiteScore);
    }

    public boolean isWhiteTurn() {
        return lastTurn == Team.BLACK;
    }

    public double score(Team team) {
        if (team == Team.BLACK) {
            return blackScore.getScore();
        }
        return whiteScore.getScore();
    }

    public Team getLastTurn() {
        return lastTurn;
    }

    public Score getBlackScore() {
        return blackScore;
    }

    public Score getWhiteScore() {
        return whiteScore;
    }
}
