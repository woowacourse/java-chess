package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Waypoints;

import java.util.Collections;

public class Knight extends Piece {

    public Knight(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
    }

    @Override
    protected void validateMovable(final Path path) {
        if (Math.abs(path.fileInterval()) == 1 && Math.abs(path.rankInterval()) == 2) {
            return;
        }
        if (Math.abs(path.fileInterval()) == 2 && Math.abs(path.rankInterval()) == 1) {
            return;
        }
        throw new IllegalArgumentException("나이트는 그렇게 움직일 수 없습니다.");
    }

    @Override
    public Waypoints waypoints(final PiecePosition destination) {
        validateMovable(Path.of(piecePosition, destination));
        return Waypoints.from(Collections.emptyList());
    }
}
