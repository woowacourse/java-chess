package chess.model.piece;

import chess.model.Team;
import chess.model.direction.route.Route;
import chess.model.position.Position;

public class Blank extends Piece {

    public Blank(final Team team) {
        super(team);
    }

    @Override
    public double addTo(final double score) {
        throw new IllegalArgumentException("[ERROR] 더 할 수 없습니다.");
    }

    @Override
    public Route findRoute(Position source, Position target) {
        throw new IllegalArgumentException("[ERROR] 빈 공간입니다.");
    }
}
