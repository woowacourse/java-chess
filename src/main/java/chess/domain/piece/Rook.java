package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MovablePaths;
import chess.domain.Path;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private static final List<Direction> DIRECTIONS;

    static {
        DIRECTIONS = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
    }

    public Rook(final TeamColor color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public MovablePaths findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : DIRECTIONS) {
            paths.add(Path.ofMultiPath(current, direction));
        }
        return new MovablePaths(paths);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

}
