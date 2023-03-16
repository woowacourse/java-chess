package domain.piece;

import domain.Square;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        return null;
    }
}
