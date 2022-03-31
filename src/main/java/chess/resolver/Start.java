package chess.resolver;

import chess.controller.ChessController;
import chess.result.StartResult;
import chess.view.Command;
import chess.view.CommandDto;
import chess.view.OutputView;

public class Start implements Resolver {

    Start() {
    }

    @Override
    public boolean canResolve(Command command) {
        return command.equals(Command.START);
    }

    @Override
    public void execute(CommandDto dto, ChessController controller) {
        final StartResult result = controller.start();
        OutputView.printChessBoard(result.getPieceByPosition());
    }
}
