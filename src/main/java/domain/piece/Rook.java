package domain.piece;

import domain.position.Position;

import java.util.List;

public class Rook extends Division{
    public Rook(Color color, Position position) {
        super(color, "r", position);
    }

    @Override
    public void move(Position to, List<Position> pieces) {
    }
}
