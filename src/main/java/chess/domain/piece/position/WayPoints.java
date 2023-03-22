package chess.domain.piece.position;

import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class WayPoints {

    private final List<PiecePosition> wayPoints;

    public WayPoints(final List<PiecePosition> wayPoints) {
        this.wayPoints = new ArrayList<>(wayPoints);
    }

    public boolean isBlocking(List<Piece> pieces) {
        return wayPoints
                .stream()
                .anyMatch(piecePosition -> existByPosition(pieces, piecePosition));
    }

    private boolean existByPosition(final List<Piece> pieces, final PiecePosition piecePosition) {
        return pieces.stream()
                .anyMatch(piece -> piece.existIn(piecePosition));
    }

    public List<PiecePosition> wayPoints() {
        return wayPoints;
    }
}
