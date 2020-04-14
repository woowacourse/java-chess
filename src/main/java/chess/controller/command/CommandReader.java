package chess.controller.command;

import utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum CommandReader {
    NONE_COMMAND_READER
            ("none", splitCommand -> new None()),
    START_COMMAND_READER
            ("start", splitCommand -> new Start()),
    MOVE_COMMAND_READER
            ("move", splitCommand -> new Move(splitCommand.get(1), splitCommand.get(2))),
    STATUS_COMMAND_READER
            ("status", splitCommand -> new Status()),
    END_COMMAND_READER
            ("end", splitCommand -> new End());

    private final String firstValue;
    private final Function<List<String>, Command> commandCreator;

    CommandReader(String firstValue, Function<List<String>, Command> commandCreator) {
        this.firstValue = firstValue;
        this.commandCreator = commandCreator;
    }

    public static Command from(String input) {
        List<String> inputs = StringUtils.splitIntoList(input);

        if (inputs.isEmpty()) {
            return new None();
        }

        return Arrays.stream(values())
                .filter(commandReader -> inputs.get(0).equals(commandReader.firstValue))
                .map(commandReader -> commandReader.commandCreator.apply(inputs))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }
}
