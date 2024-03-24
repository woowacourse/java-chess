package controller.command;

import domain.board.Position;
import domain.game.Game;
import view.CommandShape;
import view.InputView;
import view.OutputView;
import view.dto.RankInfos;

public class MoveCommand implements Command {
    private final Game game;

    public MoveCommand(final Game game) {
        this.game = game;
    }

    @Override
    public boolean execute() {
        moveByPosition(game);
        return true;
    }

    @Override
    public boolean isSameAs(final CommandShape commandShape) {
        return CommandShape.MOVE == commandShape;
    }

    private void moveByPosition(final Game game) {
        final Position source = Position.createPosition(InputView.inputCommand());
        final Position target = Position.createPosition(InputView.inputCommand());

        game.moveByPosition(source, target);
        OutputView.printChessBoard(RankInfos.of(game.board()));
    }


}
