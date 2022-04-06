package chess.state;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String userInput;

    Command(final String userInput) {
        this.userInput = userInput;
    }

    public boolean isUserInput(final String command) {
        return userInput.equals(command);
    }

    public boolean startsWith(final String command) {
        return command.startsWith(userInput);
    }
}
