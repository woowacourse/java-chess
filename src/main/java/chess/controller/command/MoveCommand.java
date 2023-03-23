package chess.controller.command;

import chess.controller.state.Move;
import chess.controller.state.State;
import chess.domain.ChessGame;

import java.util.List;
import java.util.Optional;

public class MoveCommand implements Command {

    private final String command = "move";

    @Override
    public State execute(Optional<ChessGame> chessGame, List<String> input) {
        return new Move(chessGame.orElseThrow(IllegalArgumentException::new),
                input.get(1), input.get(2));
    }

    @Override
    public String getCommand() {
        return command;
    }
}
