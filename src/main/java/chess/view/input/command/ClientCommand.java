package chess.view.input.command;

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

    public MovePath getMovePath() {
        if (command == GameCommand.MOVE) {
            return PositionConverter.convert(arguments);
        }
        throw new IllegalArgumentException("해당 명령어는 이동 경로를 받을 수 없습니다.");
    }
}
