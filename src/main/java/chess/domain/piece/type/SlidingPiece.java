package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

public abstract class SlidingPiece extends Piece{

    protected SlidingPiece(final PieceType pieceType, final Color color) {
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
        int vectorX = Integer.compare(start.findGapOfColumn(end), 0);
        int vectorY = Integer.compare(start.findGapOfRank(end), 0);

        List<Position> route = new ArrayList<>();
        Position currentPosition = start;
        do {
            currentPosition = currentPosition.moveByUnitVectorING(vectorX, vectorY);
            route.add(currentPosition);
        } while (!currentPosition.equals(end));
        return route;
    }

    protected abstract boolean isMovableMove(final Position start, final Position end);

}
