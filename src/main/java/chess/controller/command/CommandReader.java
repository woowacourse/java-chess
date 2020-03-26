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

        if ("status".equals(splitCommand.get(0))) {
            return new Status();
        }

        throw new IllegalArgumentException("존재하지 않는 명령어입니다.");
    }
}
