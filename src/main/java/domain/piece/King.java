package domain.piece;

import domain.position.Position;

import java.util.List;

public class King extends Division {
    public King(Color color, Position position) {
        super(color, "k", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {

    }
}
