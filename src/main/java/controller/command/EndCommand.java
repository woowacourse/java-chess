package controller.command;

import domain.game.Game;
import view.CommandShape;

public class EndCommand implements Command {
    private final Game game;

    public EndCommand(final Game game) {
        this.game = game;
    }

    @Override
    public boolean execute() {
        game.end();
        return false;
    }

    @Override
    public boolean isSameAs(final CommandShape commandShape) {
        return CommandShape.END == commandShape;
    }
}
