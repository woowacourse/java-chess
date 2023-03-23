package chess.game;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");
    
    private final String command;
    
    Command(String command) {
        this.command = command;
    }
    
    public static Command from(String otherCommand) {
        return Arrays.stream(values())
                .filter(command -> command.isSameCommand(otherCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다. 다시 입력해주세요."));
    }
    
    private boolean isSameCommand(String otherCommand) {
        return this.command.equals(otherCommand);
    }
    
    public boolean isStart() {
        return this == START;
    }
    
    public boolean isMove() {
        return this == MOVE;
    }
}
