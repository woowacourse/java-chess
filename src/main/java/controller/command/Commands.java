package controller.command;

import domain.game.Game;
import java.util.Arrays;
import java.util.List;
import view.CommandShape;

public class Commands {
    private List<Command> commands;

    private Commands() {
    }

    private Commands(List<Command> commands) {
        this.commands = commands;
    }

    public static Commands of(final Game game) {
        return new Commands(Arrays.asList(
                new StartCommand(game),
                new MoveCommand(game),
                new EndCommand(game)
        ));
    }

    public Command find(final CommandShape commandShape) {
        return commands.stream()
                .filter(command -> command.isSameAs(commandShape))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }
}
