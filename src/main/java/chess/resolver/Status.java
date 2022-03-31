package chess.resolver;

import chess.controller.ChessController;
import chess.domain.Score;
import chess.view.Command;
import chess.view.CommandDto;
import chess.view.OutputView;

public class Status implements Resolver {

    Status() {
    }

    @Override
    public boolean canResolve(final Command command) {
        return command.equals(Command.STATUS);
    }

    @Override
    public void execute(final CommandDto dto, final ChessController controller) {
        final Score score = controller.status();
        OutputView.printStatus(score);
    }
}
