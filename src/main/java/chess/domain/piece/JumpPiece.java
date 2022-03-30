package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public abstract class JumpPiece extends Piece {
    public JumpPiece(Team team, Position position) {
        super(team, position);
    }

    @Override
    public List<Position> findPath(Position destination) {
        Direction direction = findDirection(destination);
        return getPath(destination, direction);
    }

    protected abstract Direction findDirection(Position destination);

    private List<Position> getPath(Position destination, Direction direction) {
        return position.getPathToDst(destination, direction);
    }
}
