package chessgame.domain.state;

import chessgame.controller.Command;
import chessgame.domain.Game;
import chessgame.domain.Team;

public class Ready implements State {
    @Override
    public void changeState(Game game, Command command) {
        if (command.isNotStart()) {
            throw new IllegalArgumentException("start만 가능 합니다.");
        }
        game.setState(new WhiteTurn());
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public Team team() {
        throw new IllegalStateException("팀이 존재하지 않는 상태입니다.");
    }
}
