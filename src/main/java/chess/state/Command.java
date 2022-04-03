package chess.state;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status")
    ;

    private final String userInput;

    Command(String userInput) {
        this.userInput = userInput;
    }

    public boolean isUserInput(String command) {
        return userInput.equals(command);
    }

    public boolean startsWith(String command) {
        return userInput.startsWith(command);
    }
}
