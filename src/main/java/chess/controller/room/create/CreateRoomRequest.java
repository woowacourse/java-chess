package chess.controller.room.create;

import chess.controller.main.Request;
import java.util.Optional;

public class CreateRoomRequest {

    private final int userId;

    private CreateRoomRequest(int userId) {
        this.userId = userId;
    }

    public static CreateRoomRequest from(Request request) {
        Optional<Integer> userId = request.getUserId();
        if (userId.isEmpty()) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        return new CreateRoomRequest(userId.get());
    }

    public int getUserId() {
        return userId;
    }
}
