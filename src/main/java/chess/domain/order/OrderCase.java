package chess.domain.order;

import java.util.Arrays;

public enum OrderCase {

    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    OrderCase(String value) {
        this.value = value;
    }

    public static OrderCase from(final String input) {
        return Arrays.stream(OrderCase.values())
                .filter(orderCase -> orderCase.getValue().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력값은 start, end, move만 가능합니다."));
    }

    public String getValue() {
        return value;
    }
}
