package chess.domain.state;

import chess.domain.state.command.Command;

public interface ChessState {

    ChessState command(final Command command);

    boolean runnable();
}
