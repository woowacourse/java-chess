package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        return false;
    }


}
