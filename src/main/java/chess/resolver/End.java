package chess.resolver;

import chess.controller.ChessController;
import chess.result.EndResult;
import chess.view.Command;
import chess.view.CommandDto;
import chess.view.OutputView;

public class End implements Resolver {

    End() {
    }

    @Override
    public boolean canResolve(final Command command) {
        return command.equals(Command.END);
    }

    @Override
    public void execute(final CommandDto dto, final ChessController controller) {
        final EndResult result = controller.end();
        OutputView.printResult(result.getScore());
    }
}
