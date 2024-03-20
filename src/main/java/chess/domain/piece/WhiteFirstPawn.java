package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public class WhiteFirstPawn extends Pawn {
    private static Set<Direction> DIRECTIONS = Direction.getWhiteFirstPawnDirection();

    public WhiteFirstPawn(Position position) {
        super(position, Color.WHITE, DIRECTIONS);
    }

    public boolean isCaptureMove(Position destination) {
        Direction direction = position.findDirectionTo(destination);
        return direction == Direction.LEFT_UP || direction == Direction.RIGHT_UP;
    }

    @Override
    public WhitePawn update(Position destination) {
        return new WhitePawn(destination);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.WHITE_PAWN;
    }
}
