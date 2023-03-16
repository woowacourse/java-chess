package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Path;
import chess.domain.TeamColor;
import chess.domain.position.Position;
import chess.view.PieceName;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int WHITE_START_RANK = 2;
    private static final int BLACK_START_RANK = 7;

    private final List<Direction> directions;

    public Pawn(TeamColor color) {
        super(color, PieceName.PAWN);
        this.directions = getDirectionsByColor();
    }

    private List<Direction> getDirectionsByColor() {
        if (color == TeamColor.BLACK) {
            return List.of(Direction.SOUTH, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
        }
        return List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.NORTH_WEST);
    }

    @Override
    public List<Path> findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofSinglePath(current, direction));
        }

        if (isStartRank(current)) {
            paths.add(Path.ofPawnStartPath(current, getForwardDirectionByColor()));
        }
        return paths;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    public boolean isAttack(Position source, Position dest) {
        return source.isOneStepForwardDiagonal(dest);
    }

    private boolean isStartRank(final Position current) {
        if (color == TeamColor.WHITE) {
            return current.getX() == WHITE_START_RANK;
        }
        return current.getX() == BLACK_START_RANK;
    }

    private Direction getForwardDirectionByColor() {
        if (color == TeamColor.WHITE) {
            return Direction.NORTH;
        }
        return Direction.SOUTH;
    }
}
