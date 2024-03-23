package view;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String message;

    Command(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
