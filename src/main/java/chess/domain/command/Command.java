package chess.domain.command;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Command {

    public static final int SECOND_INDEX = 1;
    public static final int AFTER_THIRD_INDEX = 3;

    private CommandType commandType;
    private List<String> extraInputs = new ArrayList<>();

    public Command(List<String> commands) {
        commandType = CommandType.from(commands.get(0));
        if (CommandType.from(commands.get(0)) == CommandType.MOVE) {
            this.extraInputs = commands.subList(SECOND_INDEX, AFTER_THIRD_INDEX);
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
