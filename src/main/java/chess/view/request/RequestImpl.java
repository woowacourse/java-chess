package chess.view.request;

import chess.controller.main.ActionType;
import chess.controller.main.Request;
import java.util.List;
import java.util.Optional;

public class RequestImpl implements Request {

    private static final int ACTION_TYPE_INDEX = 0;

    private final List<String> commands;
    private final Optional<Integer> boardId;
    private final Optional<Integer> userId;

    public RequestImpl(List<String> commands, Optional<Integer> userId, Optional<Integer> boardId) {
        this.commands = commands;
        this.userId = userId;
        this.boardId = boardId;
    }

    @Override
    public ActionType getActionType() {
        return ActionTypeMapper.map(commands.get(ACTION_TYPE_INDEX));
    }

    @Override
    public List<String> commands() {
        return commands;
    }

    @Override
    public Optional<Integer> getBoardId() {
        return boardId;
    }

    @Override
    public Optional<Integer> getUserId() {
        return userId;
    }
}
