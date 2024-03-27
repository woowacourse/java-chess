package chess.dto;

import java.util.List;

public class RoomRequest {

    private final RoomCommand command;
    private final String name;

    private RoomRequest(RoomCommand command, String name) {
        this.command = command;
        this.name = name;
    }

    public static RoomRequest from(List<String> inputs) {
        RoomCommand commandType = RoomCommand.from(inputs.get(0));
        return new RoomRequest(commandType, inputs.get(1));
    }

    public RoomCommand getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }
}
