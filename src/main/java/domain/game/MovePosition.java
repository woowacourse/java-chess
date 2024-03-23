package domain.game;

import java.util.List;

public class MovePosition {
    private static final int COMMAND_NAME_INDEX = 0;
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private final String commandName;
    private final List<String> positions;

    public MovePosition(List<String> commands) {
        this.commandName = commands.get(COMMAND_NAME_INDEX);
        this.positions = commands.subList(COMMAND_NAME_INDEX + 1, commands.size());
    }

    public String commandName() {
        return this.commandName;
    }

    public String source() {
        return positions.get(SOURCE_INDEX);
    }

    public String target() {
        return positions.get(TARGET_INDEX);
    }

    public int size() {
        return positions.size();
    }
}
