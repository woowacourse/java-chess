package chess.controller.dto.response;

import chess.domain.Status;

public class StatusResponse {

    private final String whiteScore;
    private final String blackScore;

    public StatusResponse(Status status) {
        this.whiteScore = String.valueOf(status.getWhiteScore());
        this.blackScore = String.valueOf(status.getBlackScore());
    }
}
