package model.piece.state;

import static model.direction.MovingPattern.E;
import static model.direction.MovingPattern.N;
import static model.direction.MovingPattern.NE;
import static model.direction.MovingPattern.NW;
import static model.direction.MovingPattern.S;
import static model.direction.MovingPattern.SE;
import static model.direction.MovingPattern.SW;
import static model.direction.MovingPattern.W;

import java.util.List;
import model.direction.MovingPattern;
import model.piece.Color;

public final class Queen extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(W, E, S, N, NW, SW, NE, SE);

    private Queen(Color color) {
        super(color, movingPatterns);
    }

    public static Queen from(Color color) {
        return new Queen(color);
    }
}
