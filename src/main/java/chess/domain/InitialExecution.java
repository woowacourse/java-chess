package chess.domain;

import chess.controller.ChessController;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum InitialExecution {
    START("start", () -> new ChessController().start()),
    END("end", () -> System.out.println("프로그램을 종료합니다.")),
    MOVE("move", () -> System.out.println("start 또는 end만 입력 가능합니다")),
    ;

    private static final String INITIAL_EXECUTION_ERROR = "start 또는 end만 입력 가능합니다";

    private final String command;
    private final Runnable runnable;

    InitialExecution(String command, Runnable runnable) {
        this.command = command;
        this.runnable = runnable;
    }

    public static InitialExecution of(String input) {
        return Arrays.stream(values())
            .filter(execution -> execution.command.equalsIgnoreCase(input.trim()))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(INITIAL_EXECUTION_ERROR));
    }

    public void run() {
        runnable.run();
    }

}
