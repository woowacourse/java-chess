package chess.controller.status;

import chess.controller.Command;
import chess.domain.chess.ChessGame;

import java.util.Optional;

public interface Controller {
    Controller checkCommand(final Command command);

    boolean isRun();

    Optional<ChessGame> findGame();
}
