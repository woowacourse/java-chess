package view.mapper;

import domain.command.Command;
import java.util.Arrays;

public enum CommandInput {

    START(Command.START, "^start$"),
    MOVE(Command.MOVE, "^move [a-h][1-8] [a-h][1-8]$"),
    END(Command.END, "^end$");

    private final Command command;
    private final String input;

    CommandInput(Command command, String input) {
        this.command = command;
        this.input = input;
    }

    public static void validateCommand(String input) {
        if (Arrays.stream(values())
                .noneMatch(commandInput -> input.matches(commandInput.input))) {
            throw new IllegalArgumentException("[ERROR] 올바른 명령어를 입력해주세요.");
        }
    }

    public static Command asCommand(String input) {
        return Arrays.stream(values())
                .filter(commandInput -> input.matches(commandInput.input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 명령어를 입력해주세요."))
                .command;
    }
}
