package chess.controller.command;

import chess.service.RoomNumber;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class CommandFactory {

    private static final int COMMAND_INDEX = 0;
    private static final int FIRST_PARAMETER_INDEX = 1;

    private static final Map<CommandType, BiFunction<RoomNumber, List<String>, Command>> COMMAND_TYPES;


    static {
        COMMAND_TYPES = new EnumMap<>(CommandType.class);
        COMMAND_TYPES.put(CommandType.START, (roomNumber, ignored) -> new StartCommand(roomNumber));
        COMMAND_TYPES.put(CommandType.MOVE, MoveCommand::new);
        COMMAND_TYPES.put(CommandType.END, (roomNumber, ignored) -> new EndCommand(roomNumber));
        COMMAND_TYPES.put(CommandType.STATUS, (roomNumber, ignored) -> new StatusCommand(roomNumber));
    }

    private CommandFactory() {
        throw new UnsupportedOperationException("인스턴스화 할 수 없는 클래스입니다.");
    }

    public static Command of(RoomNumber roomNumber, List<String> commandAndParameters) {
        final CommandType commandType = CommandType.from(commandAndParameters.get(COMMAND_INDEX));
        if (commandType.isNotAppropriateSize(commandAndParameters.size())) {
            throw new IllegalArgumentException("명령어의 형식이 올바르지 않습니다");
        }
        final List<String> parameters = commandAndParameters.subList(FIRST_PARAMETER_INDEX,
                commandAndParameters.size());
        return COMMAND_TYPES.get(commandType).apply(roomNumber, parameters);
    }
}
