package chess.view.input.command;

import chess.domain.Position;
import java.util.List;

public class ClientCommand {
    GameCommand command;
    List<String> arguments;

    public ClientCommand(GameCommand command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public GameCommand getCommand() {
        return command;
    }

    public List<Position> getFromToPositions() {
        if (command == GameCommand.MOVE) {
            return PositionConverter.convert(arguments);
        }
        return List.of();
    }
}
