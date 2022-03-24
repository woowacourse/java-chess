package chess.domain.game;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum Command {

    START("start", (GameState state, List<String> ignored) -> state.start()),
    FINISH("end", (GameState state, List<String> ignored) -> state.finish()),
    MOVE("move", (GameState state, List<String> arguments) -> state.move(arguments));

    private final String input;
    private final BiFunction<GameState, List<String>, GameState> executor;

    Command(String input, BiFunction<GameState, List<String>, GameState> executor) {
        this.input = input;
        this.executor = executor;
    }

    public static Command find(String input) {
        return Arrays.stream(values())
                .filter(value -> value.input.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 명령어가 없습니다."));
    }

    public GameState execute(GameState state, List<String> arguments) {
        return this.executor.apply(state, arguments);
    }
}
