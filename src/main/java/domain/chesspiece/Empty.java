package domain.chesspiece;

import domain.team.Team;

public class Empty extends Chesspiece {
    private static final String POINT = ".";

    public Empty() {
        super(POINT, Team.NONE);
    }
}
