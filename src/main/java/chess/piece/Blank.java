package chess.piece;

import chess.chessgame.Position;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Blank extends Piece {

    public Blank() {
        super(Type.BLANK, Color.NONE);
    }

    @Override
    public boolean isMovable(Position position) {
        return false;
    }

    @Override
    public List<Pair<Integer, Integer>> computeMiddlePosition(Position position) {
        return new ArrayList<>();
    }
}
