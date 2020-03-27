package chess.controller.command;

import utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CommandReader {
    private static final Map<String, Function<List<String>, Command>> COMMAND_CACHE = new HashMap<>();

    static {
        COMMAND_CACHE.put("start", splitCommand -> new Start());
        COMMAND_CACHE.put("move", splitCommand -> new Move(splitCommand.get(1), splitCommand.get(2)));
        COMMAND_CACHE.put("status", splitCommand -> new Status());
    }

    public static Command of(String command) {
        List<String> splitCommand = StringUtils.splitIntoList(command);

        Function<List<String>, Command> commandFunction = COMMAND_CACHE.get(splitCommand.get(0));

        if (commandFunction == null) {
            throw new IllegalArgumentException("존재하지 않는 명령어입니다.");
        }

        return commandFunction.apply(splitCommand);
    }
}
