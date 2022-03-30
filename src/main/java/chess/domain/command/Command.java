package chess.domain.command;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Command {

    private CommandType commandType;
    private List<String> extraInputs = new ArrayList<>();

    public Command(List<String> commands) {
        commandType = CommandType.from(commands.get(0));
        if (CommandType.from(commands.get(0)) == CommandType.MOVE) {
            this.extraInputs = commands.subList(1, 3);
        }
    }
    public boolean isStart() {
        return commandType == CommandType.START;
    }

    public boolean isMove() {
        return commandType == CommandType.MOVE;
    }

    public boolean isStatus() {
        return commandType == CommandType.STATUS;
    }

    public boolean isEnd() {
        return commandType == CommandType.END;
    }

    public Position getFirstPosition() {
        return Position.from(extraInputs.get(0));
    }

    public Position getSecondPosition() {
        return Position.from(extraInputs.get(1));
    }
}
