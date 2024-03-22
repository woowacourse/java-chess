package model.piece.state;

import static model.direction.MovingPattern.NE;
import static model.direction.MovingPattern.NW;
import static model.direction.MovingPattern.SE;
import static model.direction.MovingPattern.SW;

import java.util.List;
import model.direction.MovingPattern;
import model.piece.Color;

public final class Bishop extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(NW, SW, NE, SE);

    private Bishop(Color color) {
        super(color, movingPatterns);
    }

    public static Bishop from(Color color) {
        return new Bishop(color);
    }
}
