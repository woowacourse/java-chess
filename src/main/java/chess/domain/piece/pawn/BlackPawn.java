package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Set;

public final class BlackPawn extends Pawn {
    private static final Set<Direction> DIRECTIONS = Set.of(Direction.DOWN, Direction.LEFT_DOWN, Direction.RIGHT_DOWN);

    public BlackPawn() {
        super(Color.BLACK, DIRECTIONS);
    }

    @Override
    protected boolean sameWithCaptureMove(Direction direction) {
        return direction == Direction.LEFT_DOWN || direction == Direction.RIGHT_DOWN;
    }

    @Override
    public Piece update() {
        return this;
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BLACK_PAWN;
    }
}
