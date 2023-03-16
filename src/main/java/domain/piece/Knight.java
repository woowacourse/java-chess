package domain.piece;

import domain.Square;
import java.util.List;

public class Knight extends Piece {


    public Knight(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        return null;
    }
}
