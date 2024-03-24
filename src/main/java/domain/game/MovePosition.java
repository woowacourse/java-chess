package domain.game;

import java.util.List;

public class MovePosition {
    private static final int COMMAND_NAME_INDEX = 0;
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;
    private static final int VALID_POSITION_COUNT = 2;

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
        validatePositionCount();
        return positions.get(SOURCE_INDEX);
    }

    public String target() {
        validatePositionCount();
        return positions.get(TARGET_INDEX);
    }

    private void validatePositionCount() {
        if (positions.size() != VALID_POSITION_COUNT) {
            throw new IllegalStateException("위치 정보가 없습니다.");
        }
    }

    public int count() {
        return positions.size();
    }
}
