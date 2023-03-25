package chess.dao;

import chess.domain.game.state.DoneState;
import chess.domain.game.state.EndState;
import chess.domain.game.state.ExecuteState;
import chess.domain.game.state.InitState;
import chess.domain.game.state.StartState;
import java.util.Arrays;

public enum ExecuteStateMapper {

    INIT(InitState.class),
    START(StartState.class),
    END(EndState.class),
    DONE(DoneState.class),
    ;

    private final Class classType;

    ExecuteStateMapper(final Class classType) {
        this.classType = classType;
    }

    public static ExecuteStateMapper from(final ExecuteState executeState) {
        return Arrays.stream(ExecuteStateMapper.values())
                .filter(executeStateMapper -> executeStateMapper.classType == executeState.getClass())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 실행 상태입니다."));
    }
}
