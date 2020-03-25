package domain.pieces;

import domain.point.Column;
import domain.point.Point;
import domain.point.Row;
import domain.team.Team;

public class King extends Piece {
    private static final String INITIAL = "K";

    public King(Team team, Point point) {
        super(INITIAL, team, point);
    }
}
