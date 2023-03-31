package chess.view.request;

import chess.controller.main.ActionType;

public enum ActionTypeMapper {
    START(ActionType.START, "start"),
    END(ActionType.END, "end"),
    MOVE(ActionType.MOVE, "move"),
    STATUS(ActionType.STATUS, "status"),
    GAMES(ActionType.GAMES, "games"),
    CREATE(ActionType.CREATE, "create"),
    JOIN(ActionType.JOIN, "join"),
    LOGIN(ActionType.LOGIN, "login");

    private final ActionType actionType;
    private final String action;

    ActionTypeMapper(ActionType actionType, String action) {
        this.actionType = actionType;
        this.action = action;
    }

    public static ActionType map(String action) {
        for (ActionTypeMapper actionTypeMapper : ActionTypeMapper.values()) {
            if (actionTypeMapper.action.equalsIgnoreCase(action)) {
                return actionTypeMapper.actionType;
            }
        }
        throw new IllegalArgumentException("잘못된 요청입니다.");
    }
}
