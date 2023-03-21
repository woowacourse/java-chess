package chess.view.validator;

public class PlayValidator implements InputValidator {
    private static final String END_INPUT = "end";
    private static final String MOVE_INPUT = "move";
    private static final String WRONG_EXCEPTION_MESSAGE = "end나 move를 입력하셔야 합니다";
    private InputValidator next;

    private void setNext() {
        this.next = new MoveSizeValidator();
    }

    @Override
    public void validate(InputRequest inputRequest) {
        if (inputRequest.contains(ValidateType.PLAY)) {
            validateInput(inputRequest);
            next.validate(inputRequest);
            return;
        }
        setNext();
        next.validate(inputRequest);
    }

    private void validateInput(InputRequest inputRequest) {
        if (inputRequest.getValue().get(0).equals(END_INPUT)) {
            this.next = new SuccessValidator();
            return;
        }
        if (inputRequest.getValue().get(0).equals(MOVE_INPUT)) {
            this.next = new MoveSizeValidator();
            return;
        }
        throw new IllegalArgumentException(WRONG_EXCEPTION_MESSAGE);
    }
}
