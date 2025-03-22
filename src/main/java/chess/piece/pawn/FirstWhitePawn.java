package chess.piece.pawn;

import chess.board.Position;
import chess.piece.Color;
import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.PieceType;
import java.util.Set;

public final class FirstWhitePawn extends Pawn {

    public FirstWhitePawn(final Position position) {
        super(Color.WHITE, position);
    }

    @Override
    protected Set<Direction> directions() {
        return Direction.ofFirstWhitePawn();
    }

    @Override
    protected Piece update(final Position destination) {
        return new WhitePawn(destination);
    }

    @Override
    public char symbol() {
        return PieceType.PAWN.symbol();
    }
}
