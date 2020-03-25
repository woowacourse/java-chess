package domain.pieces;

import domain.point.Point;
import domain.team.Team;

public class Queen extends Piece {
    private static final String INITIAL = "Q";

    public Queen(Team team, Point point) {
        super(INITIAL, team, point);
    }
}
