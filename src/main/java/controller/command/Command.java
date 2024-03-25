package controller.command;

import java.util.List;

public interface Command {
    void execute(final List<String> commandTokens);

    boolean isSameAs(final String value);
}
