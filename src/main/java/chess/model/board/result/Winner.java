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

    public Team pickFrom() {
        Map<Team, Double> teamScores = score.teams();
        if (teamScores.get(BLACK) > teamScores.get(WHITE)) {
            return BLACK;
        }
        if (teamScores.get(BLACK) < teamScores.get(WHITE)) {
            return WHITE;
        }
        return NONE;
    }
}
