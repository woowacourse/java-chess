package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.Set;

public class BlackPawn extends Pawn {
    private static Set<Direction> DIRECTIONS = Set.of(Direction.DOWN, Direction.LEFT_DOWN, Direction.RIGHT_DOWN);

    public BlackPawn(Position position) {
        super(position, Color.BLACK, DIRECTIONS);
    }

    public boolean isCaptureMove(Position destination) {
        Direction direction = position.findDirectionTo(destination);
        return direction == Direction.LEFT_DOWN || direction == Direction.RIGHT_DOWN;
    }

    @Override
    public BlackPawn update(Position destination) {
        return new BlackPawn(destination);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BLACK_PAWN;
    }
}
