package chess.gamecommand;

import java.util.ArrayList;
import java.util.List;

public class Commands {

    private static final int COMMAND_INDEX = 0;
    
    private final List<String> commands;

    public Commands(List<String> commands) {
        validate(commands);
        this.commands = new ArrayList<>(commands);
    }

    private void validate(List<String> commands) {
        GameCommand gameCommand = GameCommand.from(commands.get(COMMAND_INDEX));
        if (gameCommand == GameCommand.MOVE && commands.size() != 3) {
            throw new IllegalArgumentException("[ERROR] move 명령어는 소스 위치와 타겟 위치를 모두 입력해야 합니다.");
        }
    }
}
