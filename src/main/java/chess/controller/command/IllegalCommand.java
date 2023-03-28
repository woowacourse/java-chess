package chess.controller.command;

import chess.controller.state.State;
import chess.domain.ChessGame;

import java.util.List;
import java.util.Optional;

public class IllegalCommand implements Command {
    @Override
    public State execute(Optional<ChessGame> chessGame, List<String> input) {
        throw new IllegalArgumentException("잘못된 명령입니다");
    }

    @Override
    public String getCommand() {
        throw new UnsupportedOperationException();
    }
}
