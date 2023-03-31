package chess.controller.game.move;

import chess.controller.exception.BoardNotFoundException;
import chess.controller.exception.LoginException;
import chess.controller.main.Request;
import java.util.List;

public class MoveRequest {

    private final int boardId;
    private final String origin;
    private final String destination;

    private MoveRequest(int boardId, String origin, String destination) {
        this.boardId = boardId;
        this.origin = origin;
        this.destination = destination;
    }

    public static MoveRequest from(Request request) {
        validateCommands(request.commands());
        validateLogin(request);
        if (request.getBoardId().isEmpty()) {
            throw new BoardNotFoundException();
        }
        String origin = request.commands().get(1);
        String destination = request.commands().get(2);
        return new MoveRequest(request.getBoardId().get(), origin, destination);
    }

    private static void validateLogin(Request request) {
        if (request.getUserId().isEmpty()) {
            throw new LoginException();
        }
    }

    private static void validateCommands(List<String> commands) {
        if (commands.size() != 3) {
            throw new IllegalArgumentException("잘못된 명령어입니다.[출발지] [도착지] 형식으로 입력해주세요.");
        }
    }

    int getBoardId() {
        return boardId;
    }

    String getOrigin() {
        return origin;
    }

    String getDestination() {
        return destination;
    }
}
