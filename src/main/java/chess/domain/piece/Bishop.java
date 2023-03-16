package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Path;
import chess.domain.TeamColor;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    public Bishop(final TeamColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public List<Path> findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofMultiPath(current, direction));
        }
        return paths;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

}
