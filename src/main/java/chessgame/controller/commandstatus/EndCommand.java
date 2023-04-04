package chessgame.controller.commandstatus;

import java.util.List;

public class EndCommand implements CommandStatus {
    @Override
    public boolean canContinue() {
        return false;
    }

    @Override
    public CommandStatus start(final List<String> commands) {
        throw new IllegalArgumentException("[ERROR] 게임이 종료된 상태입니다.");
    }

    @Override
    public CommandStatus move(final List<String> commands) {
        throw new IllegalArgumentException("[ERROR] 게임이 종료된 상태입니다.");
    }

    @Override
    public CommandStatus status() {
        throw new IllegalArgumentException("[ERROR] 게임이 종료된 상태입니다.");
    }

    @Override
    public CommandStatus end() {
        throw new IllegalArgumentException("[ERROR] 게임이 종료된 상태입니다.");
    }
}
