package chess.view.validator;

public class RoomValidator implements InputValidator {

    private static final String ROOM_NAME_LIMIT_MESSAGE = "방 이름은 45자 이하여야 합니다.";
    private static final int ROOM_NUMBER_LIMIT = 45;
    private InputValidator next;

    private void setNext() {
        this.next = new ResumeValidator();
    }

    @Override
    public void validate(InputRequest inputRequest) {
        if (inputRequest.contains(ValidateType.ROOM)) {
            validateInput(inputRequest);
        }
        setNext();
        next.validate(inputRequest);
    }

    private static void validateInput(InputRequest inputRequest) {
        if (inputRequest.size() > ROOM_NUMBER_LIMIT) {
            throw new IllegalArgumentException(ROOM_NAME_LIMIT_MESSAGE);
        }
    }
}
