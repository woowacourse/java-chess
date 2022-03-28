package chess.piece;

import chess.position.Position;

import java.util.List;

public class Blank extends Piece {

    public Blank() {
        super(Type.BLANK, Color.NONE);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }

    @Override
    public List<Position> computeBetweenTwoPositionByLine(Position source, Position target) {
        return List.of();
    }
}
