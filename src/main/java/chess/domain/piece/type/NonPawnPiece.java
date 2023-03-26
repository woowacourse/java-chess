package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.List;

public abstract class NonPawnPiece extends Piece{

    protected NonPawnPiece(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean isMovable(final Position start, final Position end, final Color colorOfDestination) {
        return isMovableMove(start,end) && isMovableDestination(colorOfDestination);
    }

    @Override
    public double getScore(final List<Piece> piecesInSameColumn) {
        return pieceType.getScore();
    }

    private boolean isMovableDestination(Color colorOfDestination) {
        return !colorOfDestination.isSameColor(color);
    }

    protected abstract boolean isMovableMove(final Position start, final Position end);

}
