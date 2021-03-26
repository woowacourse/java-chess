package chess.domain.command;

import chess.domain.Game;
import chess.domain.state.Running;

public class StartCommand extends BasicCommand {

    public StartCommand(final Game game) {
        super(game);
    }

    @Override
    public void execute(String input) {
        if (game.isRunning()) {
            throw new IllegalArgumentException("이미 게임을 진행중입니다.");
        }
        game.changeState(new Running());
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
