package controller.command;

import view.CommandShape;

public interface Command {
    boolean execute();

    boolean isSameAs(final CommandShape commandShape);
}
