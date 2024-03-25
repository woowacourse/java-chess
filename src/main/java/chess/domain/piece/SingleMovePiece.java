package chess.domain.piece;

import chess.domain.Point;

import java.util.Map;

public abstract class SingleMovePiece extends Piece {

    public SingleMovePiece(final String name, final Team team) {
        super(name, team);
    }

    public abstract boolean isMovablePoint(final Point departure, final Point destination);

    @Override
    public boolean isMovable(final Point departure, final Point destination, final Map<Point, Piece> board) {
        if (hasSameTeamPieceAtDestination(board.get(departure), board.get(destination))) {
            return false;
        }
        return isMovablePoint(departure, destination);
    }
}
