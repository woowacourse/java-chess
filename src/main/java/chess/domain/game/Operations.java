package chess.domain.game;

import java.util.List;

public class Operations {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int START_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;
    public static final String INVALID_OPERATION_EXCEPTION_MESSAGE = "해당 메서드를 수행할 수 없습니다.";

    private final List<String> operations;

    public Operations(List<String> operations) {
        this.operations = operations;
    }

    public OperationType getOperationType() {
        return OperationType.of(operations.get(OPERATION_TYPE_INDEX));
    }

    public String getFirstArgument() {
        if(operations.size() <= START_POSITION_INDEX) {
            throw new UnsupportedOperationException(INVALID_OPERATION_EXCEPTION_MESSAGE);
        }
        return operations.get(START_POSITION_INDEX);
    }

    public String getSecondArgument() {
        if(operations.size() <= DESTINATION_POSITION_INDEX) {
            throw new UnsupportedOperationException(INVALID_OPERATION_EXCEPTION_MESSAGE);
        }
        return operations.get(DESTINATION_POSITION_INDEX);
    }
}
