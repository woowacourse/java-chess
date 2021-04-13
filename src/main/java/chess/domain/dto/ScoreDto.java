package chess.domain.dto;

import chess.domain.board.Board;
import chess.domain.team.Team;
import chess.domain.team.Winner;

public class ScoreDto {

    private final double blackScore;
    private final double whiteScore;
    private final Winner winner;

    public ScoreDto(double blackScore, double whiteScore, Winner winner) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.winner = winner;
    }

    public static ScoreDto of(Board board) {
        double blackScore = 0.0;
        double whiteScore = 0.0;
        if (board.isKingDead(Team.BLACK)) {
            whiteScore = board.score(Team.WHITE);
        }
        if (board.isKingDead(Team.WHITE)) {
            blackScore = board.score(Team.BLACK);
        }
        if (!board.isAnyKingDead()) {
            whiteScore = board.score(Team.WHITE);
            blackScore = board.score(Team.BLACK);
        }
        Winner winner = board.judgeWinner();
        return new ScoreDto(blackScore, whiteScore, winner);
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public Winner getWinner() {
        return winner;
    }
}
