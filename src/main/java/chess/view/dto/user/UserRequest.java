package chess.view.dto.user;

public class UserRequest {

    private final UserCommandType commandType;
    private final String userName;

    private UserRequest(UserCommandType commandType, String userName) {
        this.commandType = commandType;
        this.userName = userName;
    }

    public static UserRequest from(String[] inputs) {
        UserCommandType commandType = UserCommandType.from(inputs[0]);
        return new UserRequest(commandType, inputs[1]);
    }

    public UserCommandType getCommandType() {
        return commandType;
    }

    public String getUserName() {
        return userName;
    }
}
