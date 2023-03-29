package chess.controller;

import java.util.function.Function;

public class ChessReadyAction {

    public static final ChessReadyAction INVALID_ACTION = new ChessReadyAction((ignore) -> {
        throw new IllegalArgumentException("해당되는 명령어가 없습니다.");
    });

    private final Function<Integer, Integer> payload;

    public ChessReadyAction(Function<Integer, Integer> payload) {
        this.payload = payload;
    }

    public int execute(int gameId) {
        return payload.apply(gameId);
    }
}

