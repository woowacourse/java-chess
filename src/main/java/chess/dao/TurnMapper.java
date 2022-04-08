package chess.dao;

import chess.domain.state.turn.*;

import java.util.Arrays;
import java.util.function.Supplier;

public enum TurnMapper {
    BLACKTURN("BlackTurn", BlackTurn::new),
    WHITETURN("WhiteTurn", WhiteTurn::new),
    BLACKWIN("BlackWin", BlackWin::new),
    WHITEWIN("WhiteWin", WhiteWin::new),
    END("End", End::new);

    private final String name;
    private Supplier<State> supplier;

    TurnMapper(String name, Supplier<State> supplier) {
        this.name = name;
        this.supplier = supplier;
    }

    public State generate() {
        return supplier.get();
    }

    public static State getStateByName(String name) {
        return Arrays.stream(values())
                .filter(turn -> turn.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 상태입니다."))
                .generate();
    }

}
