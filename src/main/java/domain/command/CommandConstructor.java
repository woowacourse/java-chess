package domain.command;

import java.util.List;

@FunctionalInterface
public interface CommandConstructor {
    Command generate(List<String> arguments);
}
