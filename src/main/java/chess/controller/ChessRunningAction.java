package chess.controller;

import java.util.List;
import java.util.function.BiConsumer;

public class ChessRunningAction {

    public static final ChessRunningAction INVALID_ACTION = new ChessRunningAction(
        (ignore1, ignore2) -> {
            throw new IllegalArgumentException("해당되는 명령어가 없습니다.");
        });

    private final BiConsumer<List<String>, Integer> payload;

    public ChessRunningAction(BiConsumer<List<String>, Integer> payload) {
        this.payload = payload;
    }

    public void execute(List<String> commandAndParameter, int gameId) {
        payload.accept(commandAndParameter, gameId);
    }
}
