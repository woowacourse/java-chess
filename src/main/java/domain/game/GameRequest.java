package domain.game;

import domain.GameCommand;
import domain.position.Position;
import java.util.Collections;
import java.util.List;

public class GameRequest {
    private static final int MOVE_SOURCE_INDEX = 0;
    private static final int MOVE_DESTINATION_INDEX = 1;

    private final GameCommand commandType;
    private final List<Position> arguments;

    public GameRequest(GameCommand commandType, List<Position> arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public static GameRequest ofNoArgument(GameCommand commandType) {
        return new GameRequest(commandType, Collections.emptyList());
    }

    public Position source() {
        checkIsMove();
        return arguments.get(MOVE_SOURCE_INDEX);
    }

    public Position destination() {
        checkIsMove();
        return arguments.get(MOVE_DESTINATION_INDEX);
    }

    private void checkIsMove() {
        if (!commandType.equals(GameCommand.MOVE)) {
            throw new IllegalArgumentException("유효하지 않은 커멘드 타입입니다.");
        }
    }

    public boolean isContinuable() {
        return commandType.isContinuable();
    }

    public boolean isStart() {
        return commandType.isStart();
    }

    public boolean isStatus() {
        return commandType.isStatus();
    }

    public boolean isSave() {
        return commandType.isSave();
    }

    public boolean isEnd() {
        return commandType.isEnd();
    }
}
