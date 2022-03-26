package chess.piece;

import chess.chessgame.Position;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    @Override
    public boolean isMovable(Position position) {
        return position.isLinear();
    }

    @Override
    public List<Pair<Integer, Integer>> computeMiddlePosition(Position position) {
        return position.computeLinearMiddle();
    }

}


