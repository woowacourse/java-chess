package chess.domain.piece;

import chess.domain.path.Direction;
import chess.domain.path.MovablePaths;
import chess.domain.path.Path;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> DIRECTIONS;

    static {
        DIRECTIONS = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    public King(final TeamColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public MovablePaths findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : DIRECTIONS) {
            paths.add(Path.ofSinglePath(current, direction));
        }
        return new MovablePaths(paths);
    }

    @Override
    public boolean canMoveToEmptySquare(final Position source, final Position dest) {
        return true;
    }

    @Override
    public boolean canAttack(final Piece target, final Position source, final Position dest) {
        return target.isDifferentColor(color);
    }

}
