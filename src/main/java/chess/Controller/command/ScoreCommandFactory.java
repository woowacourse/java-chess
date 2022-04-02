package chess.Controller.command;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class ScoreCommandFactory {

    private static Map<Command, Supplier<ScoreCommand>> cachedScoreCommand = new EnumMap<>(Command.class);

    static {
        cachedScoreCommand.put(Command.STATUS, Status::new);
        cachedScoreCommand.put(Command.END, End::new);
    }

    public static ScoreCommand from(final Command command) {
        return cachedScoreCommand.get(command).get();
    }
}
