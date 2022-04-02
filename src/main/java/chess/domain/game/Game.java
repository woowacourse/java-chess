package chess.domain.game;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import chess.controller.Command;
import chess.dto.GameRequest;
import chess.dto.GameResponse;

public class Game {

    private static final Map<Command, BiFunction<GameState, List<String>, GameState>> COMMAND_EXECUTOR
        = Map.of(Command.START, (GameState state, List<String> ignored) -> state.start(),
        Command.FINISH, (GameState state, List<String> ignored) -> state.finish(),
        Command.MOVE, (GameState state, List<String> arguments) -> state.move(arguments),
        Command.STATUS, (GameState state, List<String> ignored) -> state.status());

    private GameState state;

    public Game() {
        this.state = new Ready();
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }

    public void run(GameRequest gameRequest) {
        BiFunction<GameState, List<String>, GameState> executor = findExecutor(gameRequest.getCommand());
        state = executor.apply(state, gameRequest.getArguments());
    }

    private BiFunction<GameState, List<String>, GameState> findExecutor(Command command) {
        if (!COMMAND_EXECUTOR.containsKey(command)) {
            throw new UnsupportedOperationException("[ERROR] 명령을 실행할 수 없습니다.");
        }
        return COMMAND_EXECUTOR.get(command);
    }

    public GameResponse getResponse() {
        return state.getResponse();
    }

}
