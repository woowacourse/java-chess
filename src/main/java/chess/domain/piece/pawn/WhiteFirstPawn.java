package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.Set;

public class WhiteFirstPawn extends Pawn {
    private static final Set<Direction> DIRECTIONS = Direction.getWhiteFirstPawnDirection();

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
