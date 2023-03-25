package chess.controller;

import chess.view.request.Request;
import java.util.List;

public class Filter {

    private static final int UNSET_BOARD_ID = -1;
    private static final int COMMAND_TYPE = 0;
    private final List<String> requiresUserId;
    private final List<String> requiresBoardId;

    public Filter(List<String> requiresUserId, List<String> requiresBoardId) {
        this.requiresUserId = requiresUserId;
        this.requiresBoardId = requiresBoardId;
    }

    public void validateRequest(Request request) {
        if (isRequiresUserId(request.getCommands().get(COMMAND_TYPE)) && request.getUserId() == null) {
            throw new IllegalArgumentException("유저 아이디가 필요합니다.");
        }
        if (isRequiresBoardId(request.getCommands().get(COMMAND_TYPE)) && request.getBoardId() == UNSET_BOARD_ID) {
            throw new IllegalArgumentException("게임 아이디가 필요합니다.");
        }
    }


    private boolean isRequiresUserId(String path) {
        return requiresUserId.stream()
                .anyMatch(path::contains);
    }

    private boolean isRequiresBoardId(String path) {
        return requiresBoardId.stream()
                .anyMatch(path::contains);
    }
}
