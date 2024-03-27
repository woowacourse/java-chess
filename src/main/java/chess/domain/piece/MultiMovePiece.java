package chess.domain.piece;

import chess.domain.point.Direction;
import chess.domain.point.Point;

import java.util.Map;

public abstract class MultiMovePiece extends Piece {

    public MultiMovePiece(final Type type, final Team team) {
        super(type, team);
    }

    public abstract boolean isMovableDirection(final Point departure, final Point destination);

    @Override
    public boolean isMovable(final Point departure, final Point destination, final Map<Point, Piece> board) {
        if (hasSameTeamPieceAtDestination(board.get(departure), board.get(destination))) {
            return false;
        }
        if (hasPieceOnTheRoute(departure, destination, board)) {
            return false;
        }
        return isMovableDirection(departure, destination);
    }

    private boolean hasPieceOnTheRoute(final Point departure, final Point destination, final Map<Point, Piece> board) {
        final Direction route = departure.findRoute(destination);

        Point nextPoint = departure.add(route.file(), route.rank());
        while (!nextPoint.equals(destination)) {
            if (!board.get(nextPoint).equals(Empty.INSTANCE)) {
                return true;
            }
            nextPoint = nextPoint.add(route.file(), route.rank());
        }
        return false;
    }
}
