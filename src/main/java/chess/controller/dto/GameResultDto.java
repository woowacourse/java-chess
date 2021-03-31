package chess.controller.dto;

import chess.domain.piece.Owner;
import chess.manager.Status;

public class GameResultDto {

    private final double whiteScore;
    private final double blackScore;
    private final Owner winner;

    public GameResultDto(double whiteScore, double blackScore, Owner winner) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winner = winner;
    }

    public static GameResultDto toStatus(Status status) {
        return new GameResultDto(status.whiteScore(), status.blackScore(), status.judgeWinner());
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public Owner getWinner() {
        return winner;
    }
}
