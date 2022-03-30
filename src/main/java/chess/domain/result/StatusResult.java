package chess.domain.result;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.board.ScoreCalculator;

public class StatusResult {

    private final double blackScore;
    private final double whiteScore;

    public StatusResult(Board board) {
        ScoreCalculator calculator = new ScoreCalculator(board.getBoard());
        this.blackScore = calculator.calculateScore(Team.BLACK);
        this.whiteScore = calculator.calculateScore(Team.WHITE);
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public Team getWinner() {
        if (blackScore > whiteScore) {
            return Team.BLACK;
        }
        if (blackScore == whiteScore) {
            return Team.NEUTRALITY;
        }
        return Team.WHITE;
    }
}
