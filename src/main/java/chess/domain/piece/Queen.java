package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Path;
import chess.domain.Position;
import chess.domain.TeamColor;
import chess.view.PieceName;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH, Direction.NORTH_EAST,
                Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    public Queen(final TeamColor color) {
        super(color, PieceName.QUEEN);
    }

    @Override
    public List<Path> findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofMultiPath(current, direction));
        }
        return paths;
    }

}
