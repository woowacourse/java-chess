package domain.path;

import java.util.List;

import domain.board.piece.Piece;
import domain.path.direction.Direction;
import domain.path.location.Location;

public final class Path {

    private final PieceMove pieceMove;
    private final List<Piece> piecesInPath;

    public Path(final PieceMove pieceMove, final List<Piece> piecesInPath) {
        this.pieceMove = pieceMove;
        this.piecesInPath = piecesInPath;
    }

    public int getMoveCount() {
        return piecesInPath.size() - 1;
    }

    public Location getStartLocation() {
        return pieceMove.getStart();
    }

    public Direction getDirection() {
        return Direction.find(pieceMove.getColumnDiff(), pieceMove.getRowDiff());
    }

    public List<Piece> getPiecesInPath() {
        return piecesInPath;
    }

    @Override
    public String toString() {
        return "Path{" +
                "pieceMove=" + pieceMove +
                ", piecesInPath=" + piecesInPath +
                '}';
    }
}
