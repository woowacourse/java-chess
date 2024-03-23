package view;

import java.util.Arrays;
import java.util.List;

public enum Command {
    START("start", 1),
    END("end", 1),
    MOVE("move", 3);

    private static final int COMMAND_NAME_INDEX = 0;

    private final String name;
    private final int length;

    Command(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public static Command from(List<String> rawCommand) {
        return Arrays.stream(values())
                .filter(command -> command.isSameCommand(rawCommand) && command.isSameSize(rawCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    private boolean isSameCommand(List<String> rawCommand) {
        return this.name.equals(rawCommand.get(COMMAND_NAME_INDEX));
    }

    private boolean isSameSize(List<String> rawCommand) {
        return this.length == rawCommand.size();
    }

    public String getName() {
        return name;
    }
}
