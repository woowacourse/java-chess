package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Path;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.EAST_DOWN, Direction.EAST_UP, Direction.SOUTH_LEFT, Direction.SOUTH_RIGHT,
                Direction.WEST_DOWN, Direction.WEST_UP, Direction.NORTH_LEFT, Direction.NORTH_RIGHT);
    }

    public Knight(final TeamColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public List<Path> findAllPaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofSinglePath(current, direction));
        }
        return paths;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

}
