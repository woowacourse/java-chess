package controller.command.start;

import domain.ChessGame;
import java.util.Map;

@FunctionalInterface
public interface StartAction {
    ChessGame init(final Map<Integer, String> parameters);
}
