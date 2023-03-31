package chess.controller.game.status;

import chess.controller.main.Request;

public class StatusRequest {

    private final int boardId;

    private StatusRequest(int boardId) {
        this.boardId = boardId;
    }

    public static StatusRequest from(Request request) {
        if (request.getUserId().isEmpty()) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        int roomId = Integer.parseInt(request.commands().get(1));
        return new StatusRequest(roomId);
    }

    public int getBoardId() {
        return boardId;
    }
}
