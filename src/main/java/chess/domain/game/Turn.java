package chess.domain.game;

import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Turn {
    private final List<Color> turns;

    private Turn(List<Color> turns) {
        this.turns = new ArrayList<>(turns);
    }

    public static Turn createOnStart() {
        return new Turn(List.of(Color.WHITE, Color.BLACK));
    }

    public void process() {
        Collections.rotate(turns, -1);
    }

    public Color getCurrentTurn() {
        return turns.get(0);
    }

    @Override
    public String toString() {
        return "Turn{" +
                "turns=" + turns +
                '}';
    }
}
