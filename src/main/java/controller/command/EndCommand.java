package controller.command;

import domain.game.Game;
import java.util.List;

public class EndCommand implements Command {
    private static final String END = "end";
    private static EndCommand instance;

    private final Game game;

    private EndCommand(final Game game) {
        this.game = game;
    }

    public static EndCommand of(final Game game) {
        if (instance == null) {
            instance = new EndCommand(game);
        }
        return instance;
    }

    @Override
    public void execute(final List<String> commandTokens) {
        game.end();
    }

    @Override
    public boolean isSameAs(final String value) {
        return END.equals(value);
    }
}
