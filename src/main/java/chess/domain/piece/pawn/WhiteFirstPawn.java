package chess.domain.piece.pawn;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.Set;

public class WhiteFirstPawn extends Pawn {
    private static final Set<Direction> DIRECTIONS = Direction.getWhiteFirstPawnDirection();

    public WhiteFirstPawn() {
        super(Color.WHITE, DIRECTIONS);
    }

    public boolean isCaptureMove(Position thisPosition, Position destination) {
        Direction direction = thisPosition.findDirectionTo(destination);
        return direction == Direction.LEFT_UP || direction == Direction.RIGHT_UP;
    }

    @Override
    public Piece update() {
        return new WhitePawn();
    }

    @Override
    public PieceType pieceType() {
        return PieceType.WHITE_PAWN;
    }
}
