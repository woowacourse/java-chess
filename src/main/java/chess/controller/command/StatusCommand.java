package chess.controller.command;

import chess.controller.state.State;
import chess.controller.state.Status;
import chess.domain.ChessGame;

import java.util.List;
import java.util.Optional;

public class StatusCommand implements Command{

    public static final String command = "status";

    @Override
    public State execute(Optional<ChessGame> chessGame, List<String> input) {
        return new Status(chessGame.orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public String getCommand() {
        return command;
    }
}
