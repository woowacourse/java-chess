package chess.piece.directional.normal;

import java.util.ArrayList;
import java.util.List;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Side;
import chess.piece.directional.DirectionalPiece;

public abstract class NormalPiece extends DirectionalPiece {

    public NormalPiece(Position position, Side side) {
        super(position, side);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public List<Position> getPaths(Position targetPosition) {
        List<Position> paths = new ArrayList<>();
        final Direction direction = position.getDirectionTo(targetPosition);
        final int moveCountBeforeArrivalPosition = position.getMoveCount(targetPosition, direction) - 1;
        Position nextPosition = this.position;
        for (int next = 0; next < moveCountBeforeArrivalPosition; next++) {
            nextPosition = nextPosition.getNextPosition(direction);
            paths.add(nextPosition);
        }
        return paths;
    }
}
