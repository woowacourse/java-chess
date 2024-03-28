package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Set;

public final class BlackFirstPawn extends Pawn {
    private static final Set<Direction> DIRECTIONS = Direction.getBlackFirstPawnDirection();

    public BlackFirstPawn() {
        super(Color.BLACK, DIRECTIONS);
    }

    @Override
    protected boolean sameWithCaptureMove(Direction direction) {
        return direction == Direction.LEFT_DOWN || direction == Direction.RIGHT_DOWN;
    }

    @Override
    public Piece update() {
        return new BlackPawn();
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BLACK_FIRST_PAWN;
    }
}
