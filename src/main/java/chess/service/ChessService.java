package chess.service;

import chess.controller.CommandRequest;
import chess.controller.GameCommand;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import java.util.function.Consumer;

public class ChessService {

    private final Map<GameCommand, Consumer<CommandRequest>> commandMapper = Map.of(
            GameCommand.START, ignored -> start(),
            GameCommand.MOVE, this::move,
            GameCommand.END, ignored -> end()
    );

    private State state;

    public ChessService() {
        state = NotStarted.getInstance();
    }

    public void execute(final CommandRequest request) {
        final GameCommand gameCommand = request.getGameCommand();
        commandMapper.get(gameCommand).accept(request);
    }

    private void start() {
        state = state.start();
    }

    private void move(final CommandRequest request) {
        state = state.move(request.getFrom(), request.getTo());
    }

    private void end() {
        state = state.end();
    }

    public boolean isEnd() {
        return state == End.getInstance();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }
}
