package chess.domain.command;

import chess.domain.Game;
import chess.domain.state.End;

public class EndCommand extends BasicCommand {

    public EndCommand(final Game game) {
        super(game);
    }

    @Override
    public void execute(String input) {
        game.changeState(new End());
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
