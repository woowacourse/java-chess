package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class LimitedMovePiece extends Piece {

    protected LimitedMovePiece(PieceType pieceType, PieceColor pieceColor) {
        super(pieceType, pieceColor);
    }

    @Override
    public Path findPathInDirection(Direction direction, Position currentPosition) {
        List<Position> positions = new ArrayList<>();
        if (currentPosition.canGoTo(direction)) {
            positions.add(currentPosition.moveTo(direction));
        }
        return new Path(positions);
    }
}
