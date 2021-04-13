package chess.controller.console.dto.result;

import chess.domain.manager.GameStatus;

public class GameResultResponseDto {

    private final double whiteScore;
    private final double blackScore;
    private final String winner;

    public GameResultResponseDto(final double whiteScore, final double blackScore, final String winner) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winner = winner;
    }

    public static GameResultResponseDto from(final GameStatus gameStatus) {
        return new GameResultResponseDto(gameStatus.whiteScore(), gameStatus.blackScore(), gameStatus.judgeWinner().name());
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
