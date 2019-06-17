package model;

import java.util.HashMap;
import java.util.Map;

public class YPosition {
    private static final String Y_POSITION_RANGE_EXCEPTION_MESSAGE = "yPosition의 범위가 초과하였습니다.";
    private static Map<Integer, YPosition> yPositions = new HashMap<>();
    private final int yPosition;

    static {
        for (int i = 1; i <= 8; i++) {
            yPositions.put(i, new YPosition(i));
        }
    }

    private YPosition(final int yPosition) {
        this.yPosition = yPosition;
    }

    public static YPosition valueOf(final int yPosition) {
        if (yPosition < 1 || yPosition > 8) {
            throw new IllegalArgumentException(Y_POSITION_RANGE_EXCEPTION_MESSAGE);
        }

        return yPositions.get(yPosition);
    }
}
