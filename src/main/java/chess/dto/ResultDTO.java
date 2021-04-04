package chess.dto;

import chess.domain.game.Result;

public class ResultDTO {
    private final String blackOutcome;
    private final String whiteOutcome;

    public ResultDTO(Result result) {
        if (result == null) {
            this.blackOutcome = null;
            this.whiteOutcome = null;
            return;
        }
        this.blackOutcome = result.getBlackOutcome();
        this.whiteOutcome = result.getWhiteOutcome();
    }

    public String getBlackOutcome() {
        return blackOutcome;
    }

    public String getWhiteOutcome() {
        return whiteOutcome;
    }
}
