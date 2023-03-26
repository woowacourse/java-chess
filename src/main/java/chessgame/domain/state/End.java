package chessgame.domain.state;

import java.util.List;

import chessgame.controller.Command;
import chessgame.domain.Game;
import chessgame.domain.Team;

public class End implements State {
    private final List<Team> winners;

    public End(Team winner) {
        winners = List.of(winner);
    }

    public End(List<Team> winners) {
        this.winners = winners;
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
        if (winners.size() > 1) {
            return null;
        }
        return winners.get(0);
    }
}
