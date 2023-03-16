package chess.domain.order;

import java.util.Arrays;

public enum OrderCase {

    START("start"),
    END("end"),
    MOVE("move"),
    NOTHING("nothing");

    private final String value;

    OrderCase(String value) {
        this.value = value;
    }

    public static OrderCase from(String input) {
        return Arrays.stream(OrderCase.values())
                .filter(orderCase -> orderCase.getValue().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력값은 start, end, move만 가능합니다."));
    }

    public static boolean isStart(String input) {
        return input.equals(START.getValue());
    }

    public static boolean isEnd(String input) {
        return input.equals(END.getValue());
    }

    public static boolean isMove(String input) {
        return input.equals(MOVE.getValue());
    }

    public String getValue() {
        return value;
    }
}
