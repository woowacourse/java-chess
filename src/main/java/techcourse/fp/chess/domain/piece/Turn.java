package techcourse.fp.chess.domain.piece;

import java.util.List;

public class Turn {

    private static final int LAST_TURN_IDX = 1;
    private static final int START_TURN_IDX = 0;

    private final List<Color> values;
    private int order = 0;

    private Turn(final List<Color> values) {
        this.values = values;
    }

    public static Turn createByStartTurn(Color color) {
        return new Turn(List.of(color, color.getOtherSide()));
    }

    public boolean isNotSameTurn(Color color) {
        return values.get(order) != color;
    }

    public void nextTurn() {
        order++;
        if (order > LAST_TURN_IDX) {
            order = START_TURN_IDX;
        }
    }
}
