package chess.view.validator;

public class ResumeValidator implements InputValidator {
    private static final String START_INPUT = "start";
    private static final String RESUME_INPUT = "resume";
    private static final String EXCEPTION_MESSAGE = "end, start나 resume를 입력해야 합니다.";
    private static final String END_INPUT = "end";

    private InputValidator next;

    private void setNext() {
        this.next = new StartValidator();
    }

    @Override
    public void validate(InputRequest inputRequest) {
        if (inputRequest.contains(ValidateType.RESUME)) {
            validateInput(inputRequest);
        }
        setNext();
        next.validate(inputRequest);
    }

    private static void validateInput(InputRequest inputRequest) {
        inputRequest.getValue()
                .stream()
                .filter(input -> input.equals(START_INPUT) || input.equals(RESUME_INPUT) || input.equals(END_INPUT))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(EXCEPTION_MESSAGE));
    }
}
