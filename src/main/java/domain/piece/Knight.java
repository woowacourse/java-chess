package domain.piece;

import domain.Square;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private static final List<Vectorr> directions = List.of(
        Vectorr.of(1, 2), Vectorr.of(2, 1),
        Vectorr.of(2, -1), Vectorr.of(1, -2),
        Vectorr.of(-1, -2), Vectorr.of(-2, -1),
        Vectorr.of(-2, 1), Vectorr.of(-1, 2));

    public Knight(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {

        Vectorr vector = dest.calculateVector(src);

        for (Vectorr direction : directions) {
            if (direction.equals(vector)) {
                return List.of(dest);
            }
        }
        return Collections.emptyList();
    }
}
