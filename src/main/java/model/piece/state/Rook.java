package model.piece.state;


import static model.direction.MovingPattern.E;
import static model.direction.MovingPattern.N;
import static model.direction.MovingPattern.S;
import static model.direction.MovingPattern.W;

import java.util.List;
import model.direction.MovingPattern;
import model.piece.Color;

public final class Rook extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(W, E, N, S);

    private Rook(Color color) {
        super(color, movingPatterns);
    }

    public static Rook from(Color color) {
        return new Rook(color);
    }
}
