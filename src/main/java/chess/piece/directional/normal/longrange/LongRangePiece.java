package chess.piece.directional.normal.longrange;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Side;
import chess.piece.directional.normal.NormalPiece;

public abstract class LongRangePiece extends NormalPiece {

    public LongRangePiece(Position position, Side side) {
        super(position, side);
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        return directions.contains(direction);
    }
}
