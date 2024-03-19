package chess.domain.piece;

import chess.domain.Point;

public class Piece {

    private final Point point;
    private final Team team;

    public Piece(Point point, Team team) {
        this.point = point;
        this.team = team;
    }
}
