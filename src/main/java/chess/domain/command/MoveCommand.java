package chess.domain.command;

import java.util.List;

public class MoveCommand implements Command {
    private final List<String> commands;

    public MoveCommand(List<String> commands) {
        validateCommandsLength(commands);
        this.commands = commands;
    }

    @Override
    public void validateCommandsLength(List<String> commands) {
        if (commands.size() != 3) {
            throw new IllegalArgumentException("move source위치 target위치 형식으로 입력해주세요.");
        }
    }

    public String getCurrentPosition() {
        return commands.get(1);
    }

    public String getNextPosition() {
        return commands.get(2);
    }
}
