package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"), MOVE("move"), STATUS("status"), END("end"), EXIT("exit");
    
    private static final int COMMAND_INDEX = 0;
    
    private static final String ERROR_COMMAND_CANNOT_FIND = "메뉴에 없는 커맨드입니다. 다시 입력해주세요.";
    
    private final String command;
    
    Command(String command) {
        this.command = command;
    }
    
    public static Command findCommandByInputCommand(String[] inputCommand) {
        return Arrays.stream(Command.values())
                     .filter(commandMenu -> commandMenu.command.equals(inputCommand[COMMAND_INDEX]))
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException(ERROR_COMMAND_CANNOT_FIND));
    }
}
