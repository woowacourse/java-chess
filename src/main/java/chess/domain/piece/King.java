package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Path;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import chess.view.PieceName;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH, Direction.NORTH_EAST,
                Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    public King(final TeamColor color) {
        super(color, PieceName.KING);
    }


    @Override
    public List<Path> findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofSinglePath(current, direction));
        }
        return paths;
    }

}
