package chess.domain.command;

import chess.domain.location.Location;
import java.util.List;

public class Command {
    private static final int GAME_COMMAND_INDEX = 0;
    private static final int SOURCE_LOCATION_INDEX = 1;
    private static final int TARGET_LOCATION_INDEX = 2;
    private final List<String> commands;

    public Command(List<String> commands) {
        this.commands = commands;
    }


    public String getGameCommand() {
        return commands.get(GAME_COMMAND_INDEX);
    }

    public Location getSourceLocation() {
        checkCommands();
        return Location.of(commands.get(SOURCE_LOCATION_INDEX));
    }

    public Location getTargetLocation() {
        checkCommands();
        return Location.of(commands.get(TARGET_LOCATION_INDEX));
    }

    private void checkCommands() {
        if (commands.size() < 3) {
            throw new IllegalArgumentException("[ERROR] 위치를 불러올 수 없습니다.");
        }
    }
}
