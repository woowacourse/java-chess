package chess.controller.game.end;

import chess.controller.exception.BoardNotFoundException;
import chess.controller.exception.LoginException;
import chess.controller.main.Request;

public class EndRequest {

    private final int boardId;

    private EndRequest(int boardId) {
        this.boardId = boardId;
    }

    public static EndRequest from(Request request) {
        if (request.getUserId().isEmpty()) {
            throw new LoginException();
        }
        if (request.getBoardId().isEmpty()) {
            throw new BoardNotFoundException();
        }
        return new EndRequest(request.getBoardId().get());
    }

    int getBoardId() {
        return boardId;
    }
}
