package chess.view;

import java.util.Arrays;

public enum ProcessCommand {
    START("start"),
    END("end");
    
    private final String command;
    
    ProcessCommand(String command) {
        this.command = command;
    }
    
    public static ProcessCommand from(String inputCommand) {
        return Arrays.stream(values())
                .filter(command -> command.isSameCommand(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start 혹은 end만 입력 가능합니다. 입력한 명령어 : " + inputCommand));
    }
    
    private boolean isSameCommand(String inputCommand) {
        return this.command.equals(inputCommand);
    }
    
    public boolean isEnd() {
        return this == END;
    }
}
