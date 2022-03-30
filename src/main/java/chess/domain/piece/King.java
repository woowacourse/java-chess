package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.utils.PossiblePositionChecker;

import java.util.Map;

public class King extends Piece {

    public King(Color color) {
        super(Type.KING, color);
    }

    @Override
    public boolean isMovableDot(Position source, Position target) {
        return PossiblePositionChecker.isMovablePositions(Direction.king(), source, target);
    }

    @Override
    public boolean isMovableLine(Position source, Position target, Map<Position, Piece> board) {
        return false;
    }

    @Override
    public boolean isDotPiece() {
        return true;
    }
}
