package chess.controller;

import java.util.List;
import java.util.function.Consumer;

public class ChessAction {

    public static final ChessAction INVALID_ACTION = new ChessAction(ignore -> {
        throw new IllegalArgumentException("해당되는 명령어가 없습니다.");
    });

    private final Consumer<List<String>> payload;

    public ChessAction(Consumer<List<String>> payload) {
        this.payload = payload;
    }

    public void execute(List<String> commandAndParameter) {
        payload.accept(commandAndParameter);
    }
}
