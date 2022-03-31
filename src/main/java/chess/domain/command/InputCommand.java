package chess.domain.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum InputCommand {
    MOVE("move"),
    START("start"),
    END("end"),
    STATUS("status");

    private List<String> commands;

    InputCommand(String... value) {
        this.commands = Arrays.asList(value);
    }

    public static InputCommand from(String command) {
        return Stream.of(InputCommand.values())
                .filter(value -> value.getCommand().equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }


    public String getCommand () {
        return commands.get(0);
    }

    public String getFirstPosition () {
        return commands.get(1);
    }

    public String getSecondPosition () {
        return commands.get(2);
    }
}
