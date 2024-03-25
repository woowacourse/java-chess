package domain.command;

import domain.Position;
import java.util.Arrays;
import java.util.List;

public class MoveCommand extends Command {
    public MoveCommand(String... options) {
        super(List.of(options));
    }

    @Override
    public <T> List<T> getOptions() {
        return options.stream()
                .map(String::toUpperCase)
                .map(s -> (T) Position.valueOf(s))
                .toList();
    }
}
