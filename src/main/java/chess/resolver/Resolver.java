package chess.resolver;

import chess.controller.ChessController;
import chess.view.Command;
import chess.view.CommandDto;

public interface Resolver {

    boolean canResolve(Command command);

    void execute(CommandDto dto, ChessController controller);
}
