package chessgame.domain.state;

import chessgame.domain.Command;
import chessgame.domain.Game;
import chessgame.domain.Team;

public class BlackTurn implements Running {
    private final Team team = Team.BLACK;

    @Override
    public void changeState(Game game, Command command) {
        if (!(command.isMove() || command.isEnd())) {
            throw new IllegalArgumentException("move와 end명령만 가능 합니다.");
        }
        if (command.isMove()) {
            game.setState(command, new WhiteTurn());
        }
        if (command.isEnd()) {
            game.setState(new End());
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public Team team() {
        return team;
    }
}
