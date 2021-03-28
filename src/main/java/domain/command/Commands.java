package domain.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Commands {

    private final List<Command> states;

    public Commands() {
        states = new ArrayList<>();
        initCommands();
    }

    private void initCommands() {
        states.add(new StartCommand());
        states.add(new EndCommand());
        states.add(new MoveCommand());
        states.add(new StatusCommand());
    }

    public Command command(List<String> inputs) {
        Optional<Command> maybeCommand = states.stream()
            .filter(command -> command.isCommand(inputs.get(0)))
            .findFirst();
        if (maybeCommand.isPresent()) {
            return maybeCommand.get();
        }
        throw new IllegalArgumentException("[Error] 올바르지 않은 명령 입니다.");
    }

}
