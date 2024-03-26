package controller.command;

import domain.game.Game;
import java.util.List;
import view.OutputView;
import view.dto.RankInfos;

public class StartCommand implements Command {
    private static final String START = "start";
    private static StartCommand instance;

    private final Game game;

    private StartCommand(final Game game) {
        this.game = game;
    }

    public static StartCommand of(final Game game) {
        if (instance == null) {
            instance = new StartCommand(game);
        }
        return instance;
    }

    @Override
    public void execute(final List<String> commandTokens) {
        game.start();
        OutputView.printChessBoard(RankInfos.of(game.board()));
    }

    @Override
    public boolean isSameAs(final String value) {
        return START.equals(value);
    }
}
