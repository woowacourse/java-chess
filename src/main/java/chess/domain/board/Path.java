package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public final class Path {

    private final List<Position> positions;

    public Path(final List<Position> positions) {
        this.positions = positions;
    }

    public boolean contains(final Position targetPosition) {
        return positions.contains(targetPosition);
    }

    public List<Position> removeObstacleInPath(final Piece piece, final Board board) {
        final List<Position> cleanPath = new ArrayList<>();
        for (Position position : positions) {
            final Piece otherPiece = board.pieceAt(position);
            if (piece.canReplace(otherPiece)) {
                cleanPath.add(position);
            }
            if (piece.blockedBy(otherPiece)) {
                break;
            }
        }
        return cleanPath;
    }

    public boolean isEmpty() {
        return positions.isEmpty();
    }
}
