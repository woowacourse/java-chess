package chess.controller;

import chess.domain.Command;
import java.util.List;

@FunctionalInterface
public interface CommandAction {
    Command get(List<String> inputs);
}
