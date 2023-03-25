package chess.view.request;

import java.util.List;

public class Request {

    private final List<String> commands;
    private final int boardId;
    private final String userId;

    public Request(List<String> commands, int boardId, String userId) {
        this.commands = commands;
        this.boardId = boardId;
        this.userId = userId;
    }

    public List<String> commands() {
        return commands;
    }
}
