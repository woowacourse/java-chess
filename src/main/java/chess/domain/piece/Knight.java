package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Direction;
import chess.domain.Path;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.EAST_EAST_SOUTH, Direction.EAST_EAST_NORTH, Direction.WEST_SOUTH_SOUTH,
                Direction.EAST_SOUTH_SOUTH,
                Direction.WEST_WEST_SOUTH, Direction.WEST_WEST_NORTH, Direction.WEST_NORTH_NORTH,
                Direction.EAST_NORTH_NORTH);
    }

    public Knight(final Camp camp) {
        super(camp, PieceType.KNIGHT);
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
