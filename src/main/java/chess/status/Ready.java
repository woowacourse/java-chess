package chess.status;

import chess.game.Command;

public final class Ready {

    private Ready() {
    }

    public static State start(final Command command) {
        if (command.isStart()) {
            return new Running();
        }

        if (command.isEnd()) {
            return new Finished();
        }

        throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
    }
}
