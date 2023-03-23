package chess.controller.command;

import chess.controller.state.Start;
import chess.controller.state.State;
import chess.domain.ChessGame;

import java.util.List;
import java.util.Optional;

public class StartCommand implements Command {

    private final String command = "start";

    @Override
    public State execute(Optional<ChessGame> chessGame, List<String> input) {
        return new Start();
    }

    @Override
    public String getCommand() {
        return command;
    }
}
