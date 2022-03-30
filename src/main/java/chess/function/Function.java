package chess.function;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.game.ChessGame;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum Function {

    START("start", (chessGame, commands) -> chessGame.start()),
    END("end", (chessGame, commands) -> chessGame.end()),
    MOVE("move", (chessGame, commands) -> chessGame.move(
            Coordinate.of(commands.get(1)),
            Coordinate.of(commands.get(2)))),
    STATUS("status", (chessGame, commands) -> {
        return;
    });

    private final String value;
    private final BiConsumer<ChessGame, List<String>> biConsumer;

    Function(String value,
             BiConsumer<ChessGame, List<String>> biConsumer) {
        this.value = value;
        this.biConsumer = biConsumer;
    }

    public static Function from(final String value) {
        return Arrays.stream(Function.values())
                .filter(function -> function.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기능입니다."));
    }

    public void doFunction(ChessGame chessGame, List<String> commands) {
        biConsumer.accept(chessGame, commands);
    }

    public boolean isStatus() {
        return this == STATUS;
    }
}
