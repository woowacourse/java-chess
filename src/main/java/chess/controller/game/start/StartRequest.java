package chess.controller.game.start;

import chess.controller.exception.BoardNotFoundException;
import chess.controller.exception.LoginException;
import chess.controller.main.Request;
import java.util.List;
import java.util.Optional;

public class StartRequest {

    private final int boardId;

    private StartRequest(int boardId) {
        this.boardId = boardId;
    }

    public static StartRequest from(Request request) {
        validateCommands(request.commands());
        if (request.getUserId().isEmpty()) {
            throw new LoginException();
        }
        Optional<Integer> boardId = request.getBoardId();
        if (boardId.isEmpty()) {
            throw new BoardNotFoundException();
        }
        return new StartRequest(boardId.get());
    }

    private static void validateCommands(List<String> commands) {
        if (commands.size() != 1) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
    }

    public int getBoardId() {
        return boardId;
    }
}
