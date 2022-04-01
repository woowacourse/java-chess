package chess.model;

import chess.model.board.Board;

public class GameResult {
    private final double whiteScore;
    private final double blackScore;
    private final Team winningTeam;

    private GameResult(double whiteScore, double blackScore, Team winningTeam) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winningTeam = winningTeam;
    }

    public static GameResult from(Board board) {
        double whiteScore = board.getTotalScore(Team.WHITE);
        double blackScore = board.getTotalScore(Team.BLACK);
        return new GameResult(whiteScore, blackScore, findWinningTeam(whiteScore, blackScore));
    }

    private static Team findWinningTeam(double whiteScore, double blackScore) {
        if (whiteScore > blackScore) {
            return Team.WHITE;
        }
        if (blackScore > whiteScore) {
            return Team.BLACK;
        }
        return Team.NONE;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public Team getWinningTeam() {
        return winningTeam;
    }
}
