package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Bishop extends Piece{
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        return false;
    }


}
