package domain.piece;

import domain.position.Position;

import java.util.List;

public class Knight extends Division {
    public Knight(Color color, Position position) {
        super(color, "n", position);
    }

    @Override
    public void move(Position to, List<Piece> pieces) {
    }

    @Override
    public void kill(Position to, List<Piece> pieces) {

    }
}
