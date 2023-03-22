package chess.common;

public enum IndexCommand {

    START_COMMAND_INDEX(0),
    SOURCE_POSITION(1),
    TARGET_POSITION(2),
    POSITION_COLUMN(0),
    POSITION_ROW(1)
    ;

    private final int value;

    IndexCommand(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
