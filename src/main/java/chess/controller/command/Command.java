package chess.controller.command;

import chess.controller.state.State;
import chess.domain.ChessGame;

import java.util.List;
import java.util.Optional;

public interface Command {

    State execute(Optional<ChessGame> chessGame, List<String> input);

    String getCommand();
}
