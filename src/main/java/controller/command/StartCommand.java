package controller.command;

import domain.game.Game;
import view.CommandShape;
import view.OutputView;
import view.dto.RankInfos;

public class StartCommand implements Command {
    private final Game game;

    public StartCommand(final Game game) {
        this.game = game;
    }

    @Override
    public boolean execute() {
        game.start();
        OutputView.printChessBoard(RankInfos.of(game.board()));
        return true;
    }

    @Override
    public boolean isSameAs(final CommandShape commandShape) {
        return CommandShape.START == commandShape;
    }
}
