package domain.piece;

import domain.Square;
import java.util.Collections;
import java.util.List;

public class King extends Piece {

    private static final List<DirectionVector> directions = List.of(DirectionVector.values());
    private static final int STEP_SIZE = 1;

    public King(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {

        Vectorr vector = dest.calculateVector(src);

        for (DirectionVector direction : directions) {
            if (direction.isSameDirection(vector) && vector.getMaxLength() == STEP_SIZE) {
                return List.of(dest);
            }
        }
        return Collections.emptyList();
    }
}
