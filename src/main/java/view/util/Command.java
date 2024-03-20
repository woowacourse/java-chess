package view.util;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end");

    private final String identifier;

    Command(String identifier) {
        this.identifier = identifier;
    }

    public boolean isSameIdentifier(String identifier) {
        return this.identifier.equals(identifier);
    }
}
