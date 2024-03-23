package view;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
