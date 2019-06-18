package chess.domain;

import java.util.Arrays;
import java.util.List;

import static chess.domain.Direction.*;

public class Pawn extends Piece {
    private static final String NAME = "p";
    private static final List<Direction> MOVEABLE_DIRECTION = Arrays.asList(N, E, S, W);

    public Pawn(Team team) {
        super(team, (source, target) -> {
            Direction direction = source.distance(target);
            return MOVEABLE_DIRECTION.stream()
                    .anyMatch(moveable -> moveable == direction);
        });
    }

    @Override
    public String getName() {
        return NAME;
    }
}
