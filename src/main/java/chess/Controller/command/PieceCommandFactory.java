package chess.Controller.command;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class PieceCommandFactory {

    private static final Map<Command, Supplier<PieceCommand>> cache = new EnumMap<>(Command.class);

    static {
        cache.put(Command.START, Start::new);
        cache.put(Command.MOVE, Move::new);
    }

    public static PieceCommand from(final Command command) {
        return cache.get(command).get();
    }
}
