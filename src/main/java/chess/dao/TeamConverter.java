package chess.dao;

import chess.domain.piece.Team;

public class TeamConverter {

    public static Team of(String team) {
        if (team.equals("BLACK")) {
            return Team.BLACK;
        }
        if (team.equals("WHITE")) {
            return Team.WHITE;
        }
        if (team.equals("NONE")) {
            return Team.NONE;
        }
        throw new IllegalArgumentException("[ERROR] 없는 팀입니다.");
    }
}
