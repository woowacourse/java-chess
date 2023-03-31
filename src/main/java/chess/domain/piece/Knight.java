package chess.domain.piece;

import chess.domain.TeamColor;
import chess.domain.path.Direction;
import chess.domain.path.MovablePaths;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final List<Direction> DIRECTIONS;
    private static final int MOVE_LIMIT = 1;

    static {
        DIRECTIONS = List.of(Direction.EAST_DOWN, Direction.EAST_UP, Direction.SOUTH_LEFT,
            Direction.SOUTH_RIGHT, Direction.WEST_DOWN, Direction.WEST_UP, Direction.NORTH_LEFT,
            Direction.NORTH_RIGHT);
    }

    public Knight(final TeamColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public MovablePaths findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : DIRECTIONS) {
            paths.add(Path.ofLimitedPath(current, direction, MOVE_LIMIT));
        }
        return new MovablePaths(paths);
    }

}
