package chess.controller.command;

import chess.domain.chessboard.Coordinate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Command {

    private static final Map<CommandType, CommandOptionAction> commandActions = Map.of(
            CommandType.START, Command::throwCanNotGetOptionsException,
            CommandType.END, Command::throwCanNotGetOptionsException,
            CommandType.MOVE, Command::getCoordinate);

    private final CommandType type;
    private final List<String> options;

    public Command(final CommandType type, final List<String> options) {
        this.type = type;
        this.options = options;
    }

    public CommandType getType() {
        return this.type;
    }

    public List<Coordinate> getCoordinate() {
        if (options.isEmpty()) {
            throw new IllegalStateException("옵션이 없습니다");
        }
        return commandActions.get(type).action(options);
    }

    private static List<Coordinate> getCoordinate(List<String> options) {
        return options.stream()
                .map(Coordinate::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<Coordinate> throwCanNotGetOptionsException(List<String> options) {
        throw new UnsupportedOperationException("옵션을 가지지 않는 명령어입니다.");
    }
}
