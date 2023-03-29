package chess.view.validator;

public class MoveSizeValidator implements InputValidator {
    private static final int COMMEND_SIZE = 3;
    private static final String WRONG_COMMEND_MESSAGE = "잘못된 형식입니다. (move source위치 target위치 - 예. move b2 b3)";
    private static final String MOVE = "move";
    private InputValidator next;

    private void setNext() {
        this.next = new NotInRangeValidator();
    }

    @Override
    public void validate(final InputRequest inputRequest) {
        if (inputRequest.contains(ValidateType.MOVE_SIZE)) {
            validateInput(inputRequest);
        }
        setNext();
        next.validate(inputRequest);
    }

    private static void validateInput(final InputRequest inputRequest) {
        if (inputRequest.getValue().size() != COMMEND_SIZE && inputRequest.getValue().get(0).equals(MOVE)) {
            throw new IllegalArgumentException(WRONG_COMMEND_MESSAGE);
        }
    }
}
