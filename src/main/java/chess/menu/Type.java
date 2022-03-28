package chess.menu;

import chess.domain.board.Board;
import chess.view.Command;
import java.util.Arrays;

public enum Type {

    START("start", new Start()),
    MOVE("move", new Move()),
    STATUS("status", new Status()),
    END("end", new End());

    private final String value;
    private final Menu menu;

    Type(String value, Menu menu) {
        this.value = value;
        this.menu = menu;
    }

    public static Type of(String value) {
        return Arrays.stream(Type.values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 명령어 입니다."));
    }

    public static boolean play(Board board, Command command) {
        Menu menu = Arrays.stream(Type.values())
                .filter(it -> it == command.getType())
                .map(it -> it.menu)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 명령어 입니다."));

        if (command.getType() == MOVE) {
                new Move().play(board, command);
        }
        return menu.play(board);
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }
}
