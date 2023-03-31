package chess.view.dto.ready;

public class ReadyRequest {

    private final ReadyCommandType commandType;
    private final String name;

    private ReadyRequest(ReadyCommandType commandType, String name) {
        this.commandType = commandType;
        this.name = name;
    }

    public static ReadyRequest from(String[] inputs) {
        ReadyCommandType commandType = ReadyCommandType.from(inputs[0]);
        return new ReadyRequest(commandType, inputs[1]);
    }

    public ReadyCommandType getCommandType() {
        return commandType;
    }

    public String getName() {
        return name;
    }
}
