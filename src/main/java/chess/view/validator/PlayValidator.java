package chess.view.validator;

import java.util.List;

public class PlayValidator implements InputValidator {
    private static final String END_INPUT = "end";
    private static final String MOVE_INPUT = "move";
    private static final String WRONG_EXCEPTION_MESSAGE = "end나 move를 입력하셔야 합니다";
    private static final String SIZE_EXCEPTION_MASSAGE = "잘못된 입력입니다.";
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 3;
    private InputValidator next;

    private void setNext() {
        this.next = new NotInRangeValidator();
    }

    @Override
    public void validate(InputRequest inputRequest) {
        if (inputRequest.notValidate(ValidateType.PLAY)) {
            setNext();
            next.validate(inputRequest);
            return;
        }
        validateInputWord(inputRequest.getValue().get(0));
        validateInputSize(inputRequest.getValue());
    }

    private void validateInputSize(List<String> input) {
        if (input.size() == MIN_SIZE || input.size() == MAX_SIZE) {
            return;
        }
        throw new IllegalArgumentException(SIZE_EXCEPTION_MASSAGE);
    }

    private void validateInputWord(String input) {
        if (input.equals(END_INPUT) || input.equals(MOVE_INPUT)) {
            return;
        }
        throw new IllegalArgumentException(WRONG_EXCEPTION_MESSAGE);
    }
}
