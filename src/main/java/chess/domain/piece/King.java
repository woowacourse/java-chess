package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Direction;
import chess.domain.Path;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> directions;

    static {
        directions = List.of(
                Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
                Direction.NORTH_EAST, Direction.NORTH_WEST,
                Direction.SOUTH_EAST, Direction.SOUTH_WEST
        );
    }

    public King(final Camp camp) {
        super(camp, PieceType.KING);
    }


    @Override
    public CheckablePaths findCheckablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofSinglePath(current, direction));
        }
        return new CheckablePaths(paths);
    }

    @Override
    public boolean canMoveToEmpty(final Position source, final Position dest) {
        return true;
    }

    @Override
    public boolean canAttack(Position source, Position dest, Piece target) {
        return isDifferentCamp(target.camp);
    }

}
