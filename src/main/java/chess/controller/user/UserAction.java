package chess.controller.user;

import java.util.List;

@FunctionalInterface
public interface UserAction {
    UserAction EMPTY = ignore -> {
    };

    void execute(final List<String> commands);
}
