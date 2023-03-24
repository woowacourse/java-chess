package chess.domain.piece;

import chess.domain.path.Direction;
import chess.domain.path.MovablePaths;
import chess.domain.path.Path;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> DIRECTIONS;

    static {
        DIRECTIONS = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    public Queen(final TeamColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public MovablePaths findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : DIRECTIONS) {
            paths.add(Path.ofNoLimitPath(current, direction));
        }
        return new MovablePaths(paths);
    }

}
