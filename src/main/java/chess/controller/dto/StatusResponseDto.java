package chess.controller.dto;

import chess.domain.manager.GameStatus;

public class StatusResponseDto {

    private final double whiteScore;
    private final double blackScore;

    public StatusResponseDto(final double whiteScore, final double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static StatusResponseDto toGameStatus(final GameStatus gameStatus) {
        return new StatusResponseDto(gameStatus.whiteScore(), gameStatus.blackScore());
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
