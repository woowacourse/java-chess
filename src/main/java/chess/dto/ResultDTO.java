package chess.dto;

import chess.domain.game.Result;

public class ResultDTO {
    public static final String NO_OUTCOME = "";
    private final String blackOutcome;
    private final String whiteOutcome;

    public ResultDTO(Result result) {
        if (result == null) {
            this.blackOutcome = NO_OUTCOME;
            this.whiteOutcome = NO_OUTCOME;
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
