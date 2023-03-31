package chess.controller.main;

import java.util.List;
import java.util.Optional;

public interface Request {

    ActionType getActionType();

    List<String> commands();

    Optional<Integer> getBoardId();

    Optional<Integer> getUserId();
}
