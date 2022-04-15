package chess.model.board.result;

import static chess.model.Team.BLACK;
import static chess.model.Team.NONE;
import static chess.model.Team.WHITE;

import chess.model.Team;
import java.util.Map;

public class Winner {

    private final Score score;

    public Winner(Score score) {
        this.score = score;
    }

    public Team team() {
        double whiteScore = score.white();
        double blackScore = score.black();
        if (blackScore > whiteScore) {
            return BLACK;
        }
        if (whiteScore > blackScore) {
            return WHITE;
        }
        return NONE;
    }
}
