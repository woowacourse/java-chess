package chess.model;

import java.util.Arrays;
import java.util.function.Consumer;

public enum GameStartCommand {
    START("start", runner -> runner.run()), END("end", runnable -> {});

    private final String commandLine;
    private final Consumer<Runnable> executor;

    GameStartCommand(final String commandLine, final Consumer<Runnable> executor) {
        this.commandLine = commandLine;
        this.executor = executor;
    }

    public static GameStartCommand findCommand(final String commandLine) {
        return Arrays.stream(values())
                .filter(gameStartCommand -> gameStartCommand.commandLine.equals(commandLine))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("잘못된 게임 시작 커맨드입니다. %s", commandLine)));
    }

    public void execute(final Runnable runnable) {
        executor.accept(runnable);
    }
}
