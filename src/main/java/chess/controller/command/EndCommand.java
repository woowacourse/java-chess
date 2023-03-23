package chess.controller.command;

import chess.controller.state.End;
import chess.controller.state.State;
import chess.domain.ChessGame;

import java.util.List;
import java.util.Optional;

public class EndCommand implements Command {

    private final String command = "end";

    @Override
    public State execute(Optional<ChessGame> chessGame, List<String> input) {
        return new End();
    }

    @Override
    public String getCommand() {
        return command;
    }
}
