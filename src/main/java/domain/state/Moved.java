package domain.state;

import domain.pieces.Pieces;

public class Moved extends Playing {
    public Moved(Pieces pieces) {
        super(StateType.MOVED, pieces);
    }
}
