package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Path;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import chess.view.PieceName;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
    }

    public Rook(final TeamColor color) {
        super(color, PieceName.ROOK);
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
