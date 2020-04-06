package chess.command;

import chess.game.ChessGame;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class CommandMapper {
    private static final Map<Predicate<String>, Function<String, Command>> mapper = new HashMap();

    static {
        mapper.put(EndCommand::isEnd, EndCommand::new);
        mapper.put(StatusCommand::isStatus, StatusCommand::new);
        mapper.put(StartCommand::isStart, StartCommand::new);
    }

    public Command getCommand(String rawCommand, ChessGame chessGame) {
        for (Map.Entry<Predicate<String>, Function<String, Command>> entry : mapper.entrySet()) {
            if (entry.getKey().test(rawCommand)) {
                return entry.getValue().apply(rawCommand);
            }
        }
        return MoveCommand.of(rawCommand, chessGame);
    }
}
