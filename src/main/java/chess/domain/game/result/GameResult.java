package chess.domain.game.result;

import chess.domain.piece.Camp;
import chess.domain.piece.PieceScore;
import java.util.Collections;
import java.util.Map;

public class GameResult {

    private final MatchResult matchResult;
    private final Map<Camp, PieceScore> scoreByCamp;

    public GameResult(MatchResult matchResult, Map<Camp, PieceScore> scoreByCamp) {
        this.matchResult = matchResult;
        this.scoreByCamp = scoreByCamp;
    }

    public GameResult(MatchResult matchResult) {
        this.matchResult = matchResult;
        this.scoreByCamp = Collections.emptyMap();
    }

    public boolean containsScore() {
        return !scoreByCamp.equals(Collections.emptyMap());
    }

    public PieceScore peekScoreOfCamp(Camp camp) {
        if (containsScore()) {
            return scoreByCamp.get(camp);
        }

        throw new IllegalStateException();
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }
}
