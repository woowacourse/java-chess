package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Blank extends Piece {

    public Blank() {
        super(Type.BLANK, Color.NONE);
    }

    @Override
    public List<Position> computeBetweenTwoPosition(Position source, Position target) {
        return new ArrayList<>();
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
