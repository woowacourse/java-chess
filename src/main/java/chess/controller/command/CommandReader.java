package chess.controller.command;

import utils.StringUtils;

import java.util.List;

public class CommandReader {
    public static Command of(String command) {
        List<String> splitCommand = StringUtils.splitIntoList(command);
        if ("start".equals(splitCommand.get(0))) {
            return new Start();
        }

        if ("move".equals(splitCommand.get(0))) {
            return new Move(splitCommand.get(1), splitCommand.get(2));
        }

        return null; // Todo: 나머지 커맨
    }
}
