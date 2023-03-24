package controller.adapter.inward;

import java.util.List;

public class CommandArguments {

    private final List<String> arguments;

    private CommandArguments(final List<String> arguments) {
        this.arguments = arguments;
    }

    public static CommandArguments of(final List<String> arguments) {
            return new CommandArguments(arguments);
    }

    public String getArgumentOf(final int index) {
        if (index < 0 || index >= arguments.size()) {
            throw new IllegalArgumentException("[ERROR] 명령 인자가 존재하지 않습니다.");
        }
        return arguments.get(index);
    }
}
