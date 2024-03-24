package chess.domain;

import static chess.domain.CommandType.MOVE;

import java.util.List;

public class Command {
    private static final int COMMAND_TYPE_POSITION = 0;
    private static final int SOURCE_POSITION = 1;
    private static final int TARGET_POSITION = 2;
    private static final int MOVE_COMMANDS_SIZE = 3;
    private static final int START_OR_END_COMMANDS_SIZE = 1;

    private final CommandType commandType;
    private final Route route;

    private Command(CommandType commandType, Route route) {
        this.commandType = commandType;
        this.route = route;
    }

    public static Command from(List<String> rawCommand) {
        validateCorrectForm(rawCommand);

        CommandType commandType = CommandType.getCommand(rawCommand.get(COMMAND_TYPE_POSITION));
        if (commandType == MOVE) {
            Route route = new Route(rawCommand.get(SOURCE_POSITION), rawCommand.get(TARGET_POSITION));
            return new Command(commandType, route);
        }
        return new Command(commandType, null);
    }

    private static void validateCorrectForm(List<String> rawCommand) {
        CommandType commandType = CommandType.getCommand(rawCommand.get(COMMAND_TYPE_POSITION));
        if (commandType == MOVE) {
            checkRawCommandSize(rawCommand, MOVE_COMMANDS_SIZE);
            return;
        }
        checkRawCommandSize(rawCommand, START_OR_END_COMMANDS_SIZE);
    }

    private static void checkRawCommandSize(List<String> rawCommand, int length) {
        if (rawCommand.size() != length) {
            throw new IllegalArgumentException("커맨드 타입에 따른 입력 형식이 올바르지 않습니다.");
        }
    }

    public boolean isTypeEqualTo(CommandType commandType) {
        return this.commandType == commandType;
    }

    public Position getSource() {
        checkCommandTypeMoveWhenGetRoute();
        return route.getSource();
    }

    public Position getTarget() {
        checkCommandTypeMoveWhenGetRoute();
        return route.getTarget();
    }

    private void checkCommandTypeMoveWhenGetRoute() {
        if (commandType != MOVE) {
            throw new IllegalArgumentException("start와 end 커맨드 타입은 경로를 갖지 않습니다.");
        }
    }
}
