package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    Pawn(final Color color) {
        super(color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        final List<Position> result = new ArrayList<>();
        int direction = 1;
        if (Color.WHITE == color) {
            direction = -1;
        }
        result.add(new Position(source.getRow() - 1, source.getColumn() + direction));
        result.add(new Position(source.getRow(), source.getColumn() + direction));
        result.add(new Position(source.getRow() + 1, source.getColumn() + direction));
        if (source.getColumn() == 1 || source.getColumn() == 6) {
            result.add(new Position(source.getRow(), source.getColumn() + 2 * direction));
        }
        return result;
    }
}
