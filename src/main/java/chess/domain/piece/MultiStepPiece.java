package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;
import java.util.List;

public abstract class MultiStepPiece extends Piece {

    public MultiStepPiece(Team team, Position position) {
        super(team, position);
    }

    public List<Position> findPath(Position destination, List<Direction> directions)
            throws IllegalArgumentException {
        Direction direction = findDirection(destination, directions);
        return getPath(destination, direction);
    }

    protected Direction findDirection(Position destination, List<Direction> directions)
            throws IllegalArgumentException {
        int colDiff = destination.getColDifference(position);
        int rowDiff = destination.getRowDifference(position);
        return Direction.findMatchDirection(colDiff, rowDiff, directions);
    }

    private List<Position> getPath(Position destination, Direction direction) {
        return position.getPathToDst(destination, direction);
    }
}
