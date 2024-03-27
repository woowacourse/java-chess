package view.mapper;

import domain.command.Command;
import domain.command.PlayCommand;
import domain.command.PositionCommand;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum CommandInput {

    START(Command.START, Pattern.compile("start")),
    MOVE(Command.MOVE, Pattern.compile("^move [a-h][1-8] [a-h][1-8]$")),
    END(Command.END, Pattern.compile("end"));

    private final Command command;
    private final Pattern pattern;

    CommandInput(Command command, Pattern pattern) {
        this.command = command;
        this.pattern = pattern;
    }

    public static Command asStartCommand(String input) {
        return Stream.of(CommandInput.START, CommandInput.END)
                .filter(commandInput -> commandInput.pattern.matcher(input).matches())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 명령어를 입력해주세요."))
                .command;
    }

    public static PlayCommand asPlayCommand(String input) {
        Command command = Stream.of(CommandInput.MOVE, CommandInput.END)
                .filter(commandInput -> commandInput.pattern.matcher(input).matches())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 명령어를 입력해주세요."))
                .command;
        if (command.isEnd()) {
            return new PlayCommand(command);
        }
        return new PlayCommand(command, new PositionCommand(input.split(" ", 2)[1]));
    }
}
