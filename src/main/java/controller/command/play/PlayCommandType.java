package controller.command.play;

import java.util.Arrays;

public enum PlayCommandType {
    END("END", new EndCommand()),
    MOVE("MOVE", new MoveCommand()),
    STATUS("STATUS", new StatusCommand()),
    SAVE("SAVE", new SaveCommand());

    private final String commandHead;
    private final PlayAction playAction;

    PlayCommandType(final String commandHead, final PlayAction playAction) {
        this.commandHead = commandHead;
        this.playAction = playAction;
    }

    public static PlayAction from(final String commandHead) {
        return Arrays.stream(PlayCommandType.values())
                .filter(type -> type.commandHead.equals(commandHead.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 진행 커맨드입니다."))
                .playAction;
    }
}
