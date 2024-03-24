package chess.domain.game;

import chess.domain.Position;
import java.util.Arrays;
import java.util.List;

public class MoveCommand extends Command {
    public MoveCommand(String... options) {
        super(options);
    }

    @Override
    public <T> List<T> getOptions() {
        return Arrays.stream(options)
                .map(String::toUpperCase)
                .map(s -> (T) Position.valueOf(s))
                .toList();
    }
}
