package chess.domain.command;

import java.util.List;

public class Commands {

    private final List<Command> commands;

    public Commands(final List<Command> commands) {
        this.commands = commands;
    }

    public Command getAppropriateCommand(String input) {
        return commands.stream()
                .filter(command -> command.isAppropriateCommand(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 커맨드입니다."));
    }

}
