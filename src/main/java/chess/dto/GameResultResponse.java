package chess.dto;

import static chess.domain.Camp.BLACK;
import static chess.domain.Camp.WHITE;

public class GameResultResponse {

    private final CampScore whiteScore;
    private final CampScore blackScore;
    private final String winCamp;

    public GameResultResponse(final double whiteScore, final double blackScore, final String winCamp) {
        this.whiteScore = new CampScore(WHITE.name(), whiteScore);
        this.blackScore = new CampScore(BLACK.name(), blackScore);
        this.winCamp = winCamp;
    }

    public CampScore getWhiteScore() {
        return whiteScore;
    }

    public CampScore getBlackScore() {
        return blackScore;
    }

    public String getWinCamp() {
        return winCamp;
    }
}
