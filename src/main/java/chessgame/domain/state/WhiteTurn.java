package chessgame.domain.state;

import chessgame.controller.Command;
import chessgame.domain.Game;
import chessgame.domain.Team;

public class WhiteTurn implements Running {
    private final Team team = Team.WHITE;

    @Override
    public void changeState(Game game, Command command) {
        if (command.isNotRunningCommand()) {
            throw new IllegalArgumentException("move와 status, end명령만 가능 합니다.");
        }
        if (command.isStatus()) {
            game.calculateScore();
        }
        if (command.isMove()) {
            game.setState(command, new BlackTurn());
        }
        if (command.isEnd()) {
            game.calculateScore();
            game.setState(new End(game.score().winner()));
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public String name() {
        return team.getName();
    }

    @Override
    public Team team() {
        return team;
    }
}
