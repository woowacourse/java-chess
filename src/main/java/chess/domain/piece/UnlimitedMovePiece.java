package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class UnlimitedMovePiece extends Piece {

    protected UnlimitedMovePiece(PieceType pieceType, PieceColor pieceColor) {
        super(pieceType, pieceColor);
    }

    @Override
    public Path findPathInDirection(Direction direction, Position currentPosition) {
        List<Position> positions = new ArrayList<>();
        while (currentPosition.canGoTo(direction)) {
            positions.add(currentPosition.moveTo(direction));
            currentPosition = currentPosition.moveTo(direction);
        }
        return new Path(positions);
    }
}
