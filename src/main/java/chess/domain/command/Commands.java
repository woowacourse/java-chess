package chess.domain.command;

import java.util.HashMap;
import java.util.Map;


public class Commands {

    private final Map<String, Command> commands;

    public Commands(final HashMap<String, Command> commands) {
        this.commands = commands;
    }

    public Command getAppropriateCommand(String input) {
        String[] key = input.split(" ");
        if (!commands.containsKey(key[0])) {
            throw new IllegalArgumentException("커맨드 입력을 확인하세요.");
        }
        Command command = commands.get(key[0]);
        command.handle(input);
        return command;
    }

}
