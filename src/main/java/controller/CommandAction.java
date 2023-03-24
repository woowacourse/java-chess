package controller;

import java.util.List;

@FunctionalInterface
public interface CommandAction {
    boolean execute(final List<String> commands);
}
