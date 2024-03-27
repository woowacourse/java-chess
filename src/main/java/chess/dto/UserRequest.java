package chess.dto;

import java.util.List;

public class UserRequest {

    private final UserCommand command;
    private final String name;

    private UserRequest(final UserCommand command, final String name) {
        this.command = command;
        this.name = name;
    }

    public static UserRequest from(final List<String> inputs) {
        UserCommand command = UserCommand.from(inputs.get(0));
        return new UserRequest(command, inputs.get(1));
    }

    public UserCommand getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }
}
