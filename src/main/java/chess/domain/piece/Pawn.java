package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Pawn extends Piece{

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        return false;
    }
}
