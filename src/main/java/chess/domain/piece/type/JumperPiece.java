package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.Collections;
import java.util.List;

public abstract class JumperPiece extends Piece {

    protected JumperPiece(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    public final boolean isMovable(final Position start, final Position end, final Piece destinationPiece) {
        return isMovableMove(start,end) && isMovableDestination(destinationPiece);
    }

    private boolean isMovableDestination(Piece destinationPiece) {
        return !destinationPiece.isSameColor(color);
    }

    @Override
    public List<Position> createRoute(final Position start, final Position end) {
        return Collections.emptyList();
    }

    protected abstract boolean isMovableMove(final Position start, final Position end);

}
