package chess.controller.status;

import chess.controller.Command;

public final class EndController implements Controller {
    EndController() {
    }

    @Override
    public Controller checkCommand(final Command command, final Runnable runnable) {
        throw new IllegalArgumentException("게임이 끝났습니다.");
    }

    @Override
    public boolean isRun() {
        return false;
    }
}
