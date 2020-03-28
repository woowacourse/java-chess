package chess.controller.command;

import java.util.List;
import utils.StringUtils;

public class CommandReader {
    public static Command of(String command) {
        List<String> splitCommand = StringUtils.splitIntoList(command);

        if ("start".equals(splitCommand.get(0))) {
            return new Start();
        }

        if ("move".equals(splitCommand.get(0))) {
            return new Move(splitCommand.get(1), splitCommand.get(2));
        }

        throw new IllegalArgumentException(command + " : 존재하지 않는 명령어입니다.");
    }
}
