package view;

public enum Menu {
    START("start"),
    MOVE("move"),
    END("end"),
    DEFAULT("default");

    private final String command;

    Menu(final String command) {
        this.command = command;
    }

    public static Menu fromInput(final String input) {
        for (final var value : Menu.values()) {
            if (value.command.equals(input)) {
                return value;
            }
        }
        throw new IllegalArgumentException("허용하지 않은 커멘드 입니다 start | end | move {} {} 로 입력해주세요.");
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
