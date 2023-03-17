package chess.domain.state;

import chess.domain.state.command.Command;

public interface ChessState {

    ChessState execute(final Command command);

    boolean executable();
}
