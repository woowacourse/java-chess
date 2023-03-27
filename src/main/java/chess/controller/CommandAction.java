package chess.controller;

import java.util.List;

@FunctionalInterface
public interface CommandAction {
    Command get(List<String> inputs);
}
