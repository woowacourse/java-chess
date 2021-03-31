package chess.controller.dto;

import chess.manager.Status;

public class StatusResponseDto {

    private final double whiteScore;
    private final double blackScore;

    public StatusResponseDto(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static StatusResponseDto toStatus(Status status) {
        return new StatusResponseDto(status.whiteScore(), status.blackScore());
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
