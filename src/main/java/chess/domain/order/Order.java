package chess.domain.order;

import chess.domain.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chess.domain.order.OrderCase.*;

public final class Order {
    private static final String DELIMITER = " ";
    private static final int SOURCE_POSITION_INDEX = 0;
    private static final int TARGET_POSITION_INDEX = 1;
    private static final int POSITION_SIZE = 2;
    private static final String ERROR_MOVE = "게임 이동은 move source target 형식으로 입력해야 합니다.";

    private final OrderCase orderCase;
    private final List<Position> moves;

    private Order(final OrderCase orderCase, final List<Position> moves) {
        this.orderCase = orderCase;
        this.moves = moves;
    }

    public static Order ofStart(final String input) {
        OrderCase value = OrderCase.from(input);
        validateStart(value);
        return new Order(value, new ArrayList<>());
    }

    private static void validateStart(final OrderCase value) {
        if (!value.equals(START)) {
            throw new IllegalArgumentException("게임을 시작하려면 start만 입력해야합니다");
        }
    }

    public static Order ofMoveOrEnd(final String input) {
        List<String> inputs = Arrays.asList(input.split(DELIMITER));
        OrderCase value = OrderCase.from(inputs.get(0));

        if (value.equals(END)) {
            return ofEnd(input);
        }
        if (value.equals(MOVE)) {
            return ofMove(value, inputs);
        }
        throw new IllegalArgumentException("잘못된 입력값입니다.");
    }

    public static Order ofEnd(final String input) {
        OrderCase value = OrderCase.from(input);

        if (!value.equals(END)) {
            throw new IllegalArgumentException("게임을 종료하려면 end만 입력해야합니다");
        }
        return new Order(value, new ArrayList<>());
    }

    private static Order ofMove(final OrderCase orderCase, final List<String> values) {
        validateInputSize(values);
        validateEachPosition(values);

        return new Order(orderCase, generatePositions(values));
    }

    private static void validateInputSize(final List<String> values) {
        if (!(values.size() == 2)) {
            throw new IllegalArgumentException(ERROR_MOVE);
        }
    }

    private static void validateEachPosition(final List<String> values) {
        for (int i = 1; i <= 2; i++) {
            validateInputPositionSize(values.get(i));
        }
    }

    private static void validateInputPositionSize(final String value) {
        if (value.length() != POSITION_SIZE) {
            throw new IllegalArgumentException(ERROR_MOVE);
        }
    }

    private static List<Position> generatePositions(final List<String> value) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            positions.add(Position.from(value.get(i)));
        }
        return positions;
    }

    public boolean isEnd() {
        return orderCase.equals(END);
    }

    public boolean isMove() {
        return orderCase.equals(MOVE);
    }

    public Position getSource() {
        return moves.get(SOURCE_POSITION_INDEX);
    }

    public Position getTarget() {
        return moves.get(TARGET_POSITION_INDEX);
    }
}
