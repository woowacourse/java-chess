package domain.classification;

import static domain.classification.OrderCase.*;

import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {

    private static final String DELIMITER = " ";
    private static final int MOVE_COMMAND_INDEX = 0;
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int INPUT_SOURCE_POSITION_INDEX = 1;
    private static final int INPUT_TARGET_POSITION_INDEX = 2;
    private static final int MOVE_FIRST_SINGLE_LETTER = 0;
    private static final int MOVE_SECOND_SINGLE_LETTER = 1;
    private static final int BASE_SOURCE_POSITION_INDEX = 0;
    private static final int BASE_TARGET_POSITION_INDEX = 1;
    private static final int POSITION_SIZE = 2;
    private static final String ERROR_MOVE = "[ERROR] 게임 이동은 move source위치 target위치(예. move b2 b3) 형식으로 입력해주세요.";

    private final OrderCase orderCase;
    private final List<Position> moves;

    private Order(OrderCase orderCase, List<Position> moves) {
        this.orderCase = orderCase;
        this.moves = moves;
    }

    public static Order of(OrderCase orderCase) {
        return new Order(orderCase, null);
    }

    public static Order of(OrderCase orderCase, String input) {
        List<String> moveOrder = Arrays.asList(input.split(DELIMITER));
        validateMoveCommand(moveOrder);
        List<Position> moves = new ArrayList<>();
        moves.add(generatePosition(moveOrder.get(INPUT_SOURCE_POSITION_INDEX)));
        moves.add(generatePosition(moveOrder.get(INPUT_TARGET_POSITION_INDEX)));
        return new Order(orderCase, moves);
    }

    private static void validateMoveCommand(final List<String> moveOrder) {
        validateMoveCommandFirstIsMove(moveOrder);
        validateMoveCommandSize(moveOrder);
        validateEachPosition(moveOrder);
    }

    private static void validateMoveCommandFirstIsMove(final List<String> moveOrder) {
        if (!moveOrder.get(MOVE_COMMAND_INDEX).equals(MOVE.getValue())) {
            throw new IllegalArgumentException(ERROR_MOVE);
        }
    }

    private static void validateMoveCommandSize(final List<String> moveOrder) {
        if (moveOrder.size() != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException(ERROR_MOVE);
        }
    }

    private static void validateEachPosition(final List<String> moveOrder) {
        validateInputPositionSize(moveOrder, INPUT_SOURCE_POSITION_INDEX);
        validateInputPositionSize(moveOrder, INPUT_TARGET_POSITION_INDEX);
    }

    private static void validateInputPositionSize(final List<String> moveOrder, int index) {
        if (moveOrder.get(index).length() != POSITION_SIZE) {
            throw new IllegalArgumentException(ERROR_MOVE);
        }
    }

    private static Position generatePosition(final String value) {
        return Position.of(
                XPosition.of(extractSingleLetter(value, MOVE_FIRST_SINGLE_LETTER)),
                YPosition.of(extractSingleLetter(value, MOVE_SECOND_SINGLE_LETTER))
        );
    }

    private static String extractSingleLetter(final String value, final int index) {
        return value.substring(index, index + 1);
    }

    public boolean isStart() {
        return orderCase.equals(START);
    }

    public boolean isEnd() {
        return orderCase.equals(END);
    }

    public boolean isStatus() {
        return orderCase.equals(STATUS);
    }

    public boolean isMove() {
        return orderCase.equals(MOVE);
    }

    public static void validateStartOrder(final String input) {
        if (!(input.equals(START.getValue()))) {
            throw new IllegalArgumentException("[ERROR] start 이외의 문자는 입력할 수 없습니다.");
        }
    }

    public Position getSource() {
        return moves.get(BASE_SOURCE_POSITION_INDEX);
    }

    public Position getTarget() {
        return moves.get(BASE_TARGET_POSITION_INDEX);
    }
}
