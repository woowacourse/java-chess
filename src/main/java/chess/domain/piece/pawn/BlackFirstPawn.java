package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.Set;

public class BlackFirstPawn extends Pawn {
    private static final Set<Direction> DIRECTIONS = Direction.getBlackFirstPawnDirection();

    public BlackFirstPawn(final Position position) {
        super(position, Color.BLACK, DIRECTIONS);
    }

    public boolean isCaptureMove(final Position destination) {
        final Direction direction = position.findDirectionTo(destination);
        return direction == Direction.LEFT_DOWN || direction == Direction.RIGHT_DOWN;
    }

    @Override
    public BlackPawn update(final Position destination) {
        return new BlackPawn(destination);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BLACK_PAWN;
    }
}
