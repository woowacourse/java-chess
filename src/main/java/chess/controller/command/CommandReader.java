package chess.controller.command;

import utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum CommandReader {
    NONE_COMMAND_READER
            ("", splitCommand -> new None()),
    START_COMMAND_READER
            ("start", splitCommand -> new Start()),
    MOVE_COMMAND_READER
            ("move", splitCommand -> new Move(splitCommand.get(1), splitCommand.get(2))),
    STATUS_COMMAND_READER
            ("status", splitCommand -> new Status());

    private final String firstValue;
    private final Function<List<String>, Command> commandCreator;

    CommandReader(String firstValue, Function<List<String>, Command> commandCreator) {
        this.firstValue = firstValue;
        this.commandCreator = commandCreator;
    }

    public static Command from(String input) {
        // todo: optional 활용해보기
        List<String> inputs;
        try {
            inputs = StringUtils.splitIntoList(input);
        } catch (NullPointerException e) {
            return new None();
        }
        //

        List<String> splitInput = inputs;

        return Arrays.stream(values())
                .filter(commandReader -> splitInput.get(0).equals(commandReader.firstValue))
                .map(commandReader -> commandReader.commandCreator.apply(splitInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }
}
