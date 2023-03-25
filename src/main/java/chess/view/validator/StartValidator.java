package chess.view.validator;

public class StartValidator implements InputValidator {
    private static final String START_INPUT = "start";
    private static final String END_INPUT = "end";
    private static final String EXCEPTION_MESSAGE = "start나 end를 입력해야 합니다.";
    private InputValidator next;

    private void setNext() {
        this.next = new PlayValidator();
    }

    @Override
    public void validate(final InputRequest inputRequest) {
        if (inputRequest.contains(ValidateType.START)) {
            validateInput(inputRequest);
        }
        setNext();
        next.validate(inputRequest);
    }

    private static void validateInput(final InputRequest inputRequest) {
        inputRequest.getValue()
                .stream()
                .filter(input -> input.equals(START_INPUT) || input.equals(END_INPUT))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(EXCEPTION_MESSAGE));
    }
}
