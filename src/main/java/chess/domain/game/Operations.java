package chess.domain.game;

import java.util.List;

public class Operations {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int START_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;

    private final List<String> operations;

    public Operations(List<String> operations) {
        this.operations = operations;
    }

    public OperationType getOperationType() {
        return OperationType.of(operations.get(OPERATION_TYPE_INDEX));
    }

    public String getFirstArgument() {
        return operations.get(START_POSITION_INDEX);
    }

    public String getSecondArgument() {
        return operations.get(DESTINATION_POSITION_INDEX);
    }
}
