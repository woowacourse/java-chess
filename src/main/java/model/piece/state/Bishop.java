package model.piece.state;

import static model.direction.Direction.NE;
import static model.direction.Direction.NW;
import static model.direction.Direction.SE;
import static model.direction.Direction.SW;

import java.util.List;
import model.direction.Direction;
import model.piece.Color;

public final class Bishop extends Role {
    private static final List<Direction> DIRECTIONS = List.of(NW, SW, NE, SE);

    private Bishop(Color color) {
        super(color, DIRECTIONS);
    }

    public static Bishop from(Color color) {
        return new Bishop(color);
    }
}
