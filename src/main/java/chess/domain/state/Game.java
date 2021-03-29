package chess.domain.state;

import chess.domain.Result;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.team.PieceSet;
import chess.domain.team.Score;

import java.util.HashMap;
import java.util.Map;

public abstract class Game implements GameState {

    @Override
    public Result result(PieceSet black, PieceSet white) {
        Map<TeamColor, Score> result = teamScores(black, white);

        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) > 0) {
            return new Result(result, TeamColor.BLACK);
        }
        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) < 0) {
            return new Result(result, TeamColor.WHITE);
        }

        return new Result(result, TeamColor.NONE);
    }


    private Map<TeamColor, Score> teamScores(PieceSet black, PieceSet white) {
        Map<TeamColor, Score> result = new HashMap<>();
        result.put(TeamColor.BLACK, black.calculateScore());
        result.put(TeamColor.WHITE, white.calculateScore());
        return result;
    }
}
