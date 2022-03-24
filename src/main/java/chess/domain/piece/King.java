package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class King extends Piece{

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        return false;
    }


}
