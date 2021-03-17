package domain.piece;

import domain.position.Position;

import java.util.List;

public class Queen extends Division{
    public Queen(Color color, Position position) {
        super(color, "q", position);
    }

    @Override
    public void move(Position to, List<Position> pieces) {
    }
}
