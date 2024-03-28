package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Set;

public final class WhitePawn extends Pawn {
    private static final Set<Direction> DIRECTIONS = Set.of(Direction.UP, Direction.LEFT_UP, Direction.RIGHT_UP);

    public WhitePawn() {
        super(Color.WHITE, DIRECTIONS);
    }

    @Override
    protected boolean sameWithCaptureMove(Direction direction) {
        return direction == Direction.LEFT_UP || direction == Direction.RIGHT_UP;
    }

    @Override
    public Piece update() {
        return this;
    }

    @Override
    public PieceType pieceType() {
        return PieceType.WHITE_PAWN;
    }
}
