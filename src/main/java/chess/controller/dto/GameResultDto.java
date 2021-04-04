package chess.controller.dto;

import chess.domain.piece.Owner;
import chess.domain.manager.GameStatus;

public class GameResultDto {

    private final double whiteScore;
    private final double blackScore;
    private final Owner winner;

    public GameResultDto(final double whiteScore, final double blackScore, final Owner winner) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winner = winner;
    }

    public static GameResultDto toStatus(final GameStatus gameStatus) {
        return new GameResultDto(gameStatus.whiteScore(), gameStatus.blackScore(), gameStatus.judgeWinner());
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
