package chess;

import chess.domain.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.dto.ResponseDto;

public class ChessController {
    private State state;

    public ChessController(final State state) {
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public ResponseDto progress(Command command) {
        try {
            state = command.changeChessState(state);
        } catch (IllegalArgumentException ex) {
            return new ResponseDto(400, ex.getMessage(), state.isGameOver());
        }

        return new ResponseDto(200, "", state.isGameOver());
    }

    public void restart() {
        state = new Ready( new BoardInitializer().init());
        start();
    }

    public State state() {
        return state;
    }
}
