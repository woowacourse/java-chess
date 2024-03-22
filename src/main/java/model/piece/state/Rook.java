package model.piece.state;


import static model.direction.Direction.E;
import static model.direction.Direction.N;
import static model.direction.Direction.S;
import static model.direction.Direction.W;

import java.util.List;
import model.direction.Direction;
import model.piece.Color;

public final class Rook extends MultiShiftRole {
    private static final List<Direction> DIRECTIONS = List.of(W, E, N, S);

    private Rook(Color color) {
        super(color, DIRECTIONS);
    }

    public static Rook from(Color color) {
        return new Rook(color);
    }
}
