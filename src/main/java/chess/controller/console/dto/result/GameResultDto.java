package chess.controller.console.dto.result;

import chess.domain.manager.GameStatus;
import chess.domain.piece.Owner;

public class GameResultDto {

    private final double whiteScore;
    private final double blackScore;
    private final String winner;

    public GameResultDto(final double whiteScore, final double blackScore, final String winner) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winner = winner;
    }

    public static GameResultDto from(final GameStatus gameStatus) {
        return new GameResultDto(gameStatus.whiteScore(), gameStatus.blackScore(), gameStatus.judgeWinner().name());
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public String getWinner() {
        return winner;
    }
}
