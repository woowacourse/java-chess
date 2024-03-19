package domain;

import java.util.List;

public class Rook extends Piece {

    public Rook(Side side) {
        super(side);
    }

    public List<Horizontal> initHorizontal() {
        return List.of(Horizontal.A, Horizontal.H);
    }
}
