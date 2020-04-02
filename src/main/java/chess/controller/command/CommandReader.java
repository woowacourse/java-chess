package chess.controller.command;

import chess.view.IllegalUserInputException;
import java.util.List;
import utils.StringUtils;

public class CommandReader {

    public static Command of(String command) {
        List<String> splitCommand = StringUtils.splitIntoList(command);

        if (Start.command.equals(splitCommand.get(0))) {
            return new Start();
        }
        if (Move.command.equals(splitCommand.get(0))) {
            return executeMove(splitCommand);
        }
        if (Status.command.equals(splitCommand.get(0))) {
            return new Status();
        }
        if (End.command.equals(splitCommand.get(0))) {
            return new End();
        }
        throw new IllegalArgumentException(command + " : 존재하지 않는 명령어입니다.");
    }

    private static Move executeMove(List<String> splitCommand) {
        if (splitCommand.size() <= Move.argumentCount) {
            throw new IllegalUserInputException("move 명령에 인수가 부족합니다.");
        }
        return new Move(splitCommand.get(1), splitCommand.get(2));
    }
}
