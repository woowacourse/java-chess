package model.piece.state;

import static model.direction.Direction.E;
import static model.direction.Direction.N;
import static model.direction.Direction.NE;
import static model.direction.Direction.NW;
import static model.direction.Direction.S;
import static model.direction.Direction.SE;
import static model.direction.Direction.SW;
import static model.direction.Direction.W;

import java.util.List;
import model.direction.Direction;
import model.piece.Color;

public final class King extends SingleShiftRole {
    private static final List<Direction> DIRECTIONS = List.of(N, NE, E, SE, S, SW, W, NW);

    private King(Color color) {
        super(color, DIRECTIONS);
    }

    public static King from(Color color) {
        return new King(color);
    }
}
