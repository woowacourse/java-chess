package view.dto;

import java.util.List;
import view.util.Command;

public record Commands(List<String> commands) {

    public Command command() {
        return Command.from(commands.get(0));
    }

    public CoordinateRequest startCoordinate() {
        return CoordinateRequest.fromCommand(commands.get(1));
    }

    public CoordinateRequest destinationCoordinate() {
        return CoordinateRequest.fromCommand(commands.get(2));
    }
}
