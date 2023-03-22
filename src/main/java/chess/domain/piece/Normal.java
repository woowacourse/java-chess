package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashMap;
import java.util.Map;

public abstract class Normal extends Piece {

    public Normal(Color color) {
        super(color);
    }

    public boolean canMove(Map<Position, Boolean> isEmptyPosition, Position source, Position target) {
        isEmptyPosition = new HashMap<>(isEmptyPosition);
        isEmptyPosition.remove(target);

        return isEmptyPosition.keySet()
                .stream()
                .allMatch(isEmptyPosition::get);
    }
}
