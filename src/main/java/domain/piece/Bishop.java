package domain.piece;

import domain.position.Position;

import java.util.List;

public class Bishop extends Division{
    public Bishop(Color color, Position position) {
        super(color, "b", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {

    }
}
