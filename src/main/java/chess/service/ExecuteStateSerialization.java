package chess.service;

import chess.domain.game.state.DoneState;
import chess.domain.game.state.EndState;
import chess.domain.game.state.ExecuteState;
import chess.domain.game.state.StartState;
import java.util.Map;

public class ExecuteStateSerialization {

    private static final Map<String, ExecuteState> deserialization = Map.of(
            "START", StartState.CACHE,
            "END", EndState.CACHE,
            "DONE", DoneState.CACHE
    );

    public static String serialize(final ExecuteState executeState) {
        if (executeState.isRunning()) {
            return "START";
        }
        if (executeState.isEnd()) {
            return "END";
        }
        if (executeState.isDone()) {
            return "DONE";
        }
        throw new IllegalArgumentException("직렬화할 수 없는 상태입니다.");
    }

    public static ExecuteState deserialize(final String state) {
        if (!deserialization.containsKey(state)) {
            throw new IllegalArgumentException("역직렬화할 수 없는 상태입니다.");
        }
        return deserialization.get(state);
    }
}
