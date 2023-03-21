package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum MoveCommand {

    MOVE("move", 3),
    END("end", 1);

    private final String value;
    private final int length;

    MoveCommand(String value, int length) {
        this.value = value;
        this.length = length;
    }

    public static Optional<MoveCommand> of(List<String> commands) {
        return Arrays.stream(values())
                .filter(command -> command.length == commands.size())
                .filter(command -> command.value.equals(commands.get(0)))
                .findAny();
    }
}
