package chess.domain.order;

import java.util.Arrays;
import java.util.List;

public enum OrderCase {

    START("start", 1),
    END("end", 1),
    MOVE("move", 3);

    private final String value;
    private final int length;

    OrderCase(final String value, final int length) {
        this.value = value;
        this.length = length;
    }

    public static OrderCase from(final List<String> input) {
        return Arrays.stream(OrderCase.values())
                .filter(orderCase -> isMatch(input, orderCase))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력값은 start, end, move만 가능합니다."));
    }

    private static boolean isMatch(final List<String> input, final OrderCase orderCase) {
        return isSameCommand(input, orderCase) && isSameLength(input, orderCase);
    }

    private static boolean isSameCommand(final List<String> input, final OrderCase orderCase) {
        return orderCase.value.equals(input.get(0));
    }

    private static boolean isSameLength(final List<String> input, final OrderCase orderCase) {
        return input.size() == orderCase.length;
    }

    public String getValue() {
        return value;
    }
}
