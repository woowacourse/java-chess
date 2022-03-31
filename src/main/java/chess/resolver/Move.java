package chess.resolver;

import chess.controller.ChessController;
import chess.result.MoveResult;
import chess.view.Command;
import chess.view.CommandDto;
import chess.view.OutputView;

public class Move implements Resolver {

    Move() {
    }

    @Override
    public boolean canResolve(final Command command) {
        return command.equals(Command.MOVE);
    }

    @Override
    public void execute(final CommandDto dto, final ChessController controller) {
        final MoveResult result = controller.move(dto.getFrom(), dto.getTo());
        OutputView.printChessBoard(result.getPieceByPosition());
        if (result.isKingDie()) {
            OutputView.printResult(result.score());
        }
    }
}
