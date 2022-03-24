package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Rook extends Piece{
    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        return false;
    }


}
