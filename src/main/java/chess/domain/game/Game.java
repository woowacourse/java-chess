package chess.domain.game;

import chess.dto.Request;
import chess.dto.Response;

public class Game {

    private GameState state;

    public Game() {
        state = new Ready();
    }

    public void execute(Request request) {
        Command command = Command.find(request.getCommand());
        state = command.execute(state, request.getArguments());
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }

    public Response getResponse() {
        return state.getResponse();
    }
}
