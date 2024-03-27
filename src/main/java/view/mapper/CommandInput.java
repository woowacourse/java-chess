package view.mapper;

import controller.Command;
import java.util.Arrays;

public enum CommandInput {

    START(Command.START, "start"),
    MOVE(Command.MOVE, "move"),
    END(Command.END, "end");

    private final Command command;
    private final String input;

    CommandInput(Command command, String input) {
        this.command = command;
        this.input = input;
    }

    public static Command asCommand(String input) {
        return Arrays.stream(values())
                .filter(commandInput -> input.equals(commandInput.input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 명령어를 입력해주세요."))
                .command;
    }
}
