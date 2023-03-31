package chess.controller.room.join;

import chess.controller.exception.LoginException;
import chess.controller.main.Request;
import java.util.List;

public class JoinBoardRequest {

    private static final int ROOM_ID_INDEX = 1;

    private final int roomId;

    public JoinBoardRequest(int roomId) {
        this.roomId = roomId;
    }

    public static JoinBoardRequest from(Request request) {
        List<String> commands = request.commands();
        validateCommands(commands);
        if (request.getUserId().isEmpty()) {
            throw new LoginException();
        }
        int roomId = Integer.parseInt(commands.get(ROOM_ID_INDEX));
        return new JoinBoardRequest(roomId);
    }

    private static void validateCommands(List<String> commands) {
        if (commands.size() != 2) {
            throw new IllegalArgumentException("잘못된 명령어입니다. join [방번호] 형식으로 입력해주세요.");
        }
    }

    public int getRoomId() {
        return roomId;
    }
}
