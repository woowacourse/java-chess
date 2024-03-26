package controller.command;

import domain.game.Game;
import java.util.Arrays;
import java.util.List;

public class Commands {
    private List<Command> commands;

    private Commands() {
    }

    private Commands(List<Command> commands) {
        this.commands = commands;
    }

    public static Commands of(final Game game) {
        return new Commands(Arrays.asList(
                StartCommand.of(game),
                MoveCommand.of(game),
                EndCommand.of(game)
        ));
    }

    public Command find(final String value) {
        return commands.stream()
                .filter(command -> command.isSameAs(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }
}
