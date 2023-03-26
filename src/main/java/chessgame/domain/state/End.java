package chessgame.domain.state;

import chessgame.controller.Command;
import chessgame.domain.Game;
import chessgame.domain.Team;

public class End implements State {
    private Team team;

    public End() {
    }

    public End(Team team) {
        this.team = team;
    }

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
        return false;
    }

    @Override
    public Team team() {
        return team;
    }
}
