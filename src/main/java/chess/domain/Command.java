package chess.domain;

import java.util.Arrays;
import java.util.function.Consumer;

public enum Command {
    START("START", true, ChessGame::initialize),
    END("END", false, ChessGame::finish),
    MOVE("MOVE", true, chessGame -> {
    }),
    STATUS("STATUS", false, chessGame -> {
    });

    private final String command;
    private final boolean prints;
    private final Consumer<ChessGame> execution;

    Command(final String command, final boolean prints, Consumer<ChessGame> execution) {
        this.command = command;
        this.prints = prints;
        this.execution = execution;
    }

    public void execute(final ChessGame chessGame) {
        this.execution.accept(chessGame);
    }

    public static boolean validatesCommand(final String command) {
        return Arrays.stream(Command.values())
                .anyMatch(cmd -> cmd.command.equals(command));
    }

    public boolean prints() {
        return this.prints;
    }
}
