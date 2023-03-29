package chess.domain.state;

import chess.domain.piece.Color;
import chess.domain.state.command.Command;

public interface ChessState {

    ChessState changeStateByCommand(final Command command);

    boolean isRunnable();

    boolean isCheckmate();

    ChessState changeTurn();

    boolean isInCorrectTurn(final Color color);

    ChessState finish();

    String findCurrentTurn();
}
