package domain.game;

import java.util.ArrayList;
import java.util.List;

public class MovePosition {
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;
    private final List<String> positions;

    public MovePosition(List<String> positions) {
        this.positions = new ArrayList<>(positions);
    }

    public String source() {
        return positions.get(SOURCE_INDEX);
    }

    public String target() {
        return positions.get(TARGET_INDEX);
    }
}
