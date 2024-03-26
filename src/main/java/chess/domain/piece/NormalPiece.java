package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Map.Entry;

public abstract class NormalPiece extends Piece {

    protected NormalPiece(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    protected boolean filterMovement(Movement movement, Position source, Position target, Map<Position, Piece> pieces) {
        return true;
    }

    @Override
    protected boolean filterObstacles(Position source, Position target, Entry<Position, Piece> entry) {
        return entry.getValue().isSameColor(this.getColor());
    }
}
