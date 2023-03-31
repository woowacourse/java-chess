package chess.controller.status;

import chess.controller.Command;
import chess.domain.chess.ChessGame;

import java.util.Optional;

public final class EndController implements Controller {
    EndController() {
    }

    @Override
    public Controller checkCommand(final Command command) {
        throw new IllegalArgumentException("게임이 끝났습니다.");
    }

    @Override
    public boolean isRun() {
        return false;
    }

    @Override
    public Optional<ChessGame> findGame() {
        return Optional.empty();
    }
}
