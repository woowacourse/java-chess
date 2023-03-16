package domain.piece;

import domain.Square;
import java.util.List;

public class Queen extends Piece {

    private static final List<DirectionVector> directions = List.of(DirectionVector.values());
    public Queen(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        return null;
    }
}
