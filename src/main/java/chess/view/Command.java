package chess.view;

import chess.domain.ChessGame;

import java.util.Arrays;
import java.util.function.Consumer;

public enum Command {
    START("START", true, ChessGame::initSetting),
    END("END", false, ChessGame::end),
    MOVE("MOVE", true, chessGame -> {
    }),
    STATUS("STATUS", false, chessGame -> {
    });

    private final String command;
    private final boolean isPrint;
    private final Consumer<ChessGame> execution;

    Command(final String command, final boolean isPrint, Consumer<ChessGame> execution) {
        this.command = command;
        this.isPrint = isPrint;
        this.execution = execution;
    }

    public void execute(final ChessGame chessGame) {
        this.execution.accept(chessGame);
    }

    public static boolean isValidateCommand(final String command) {
        return Arrays.stream(Command.values())
                .anyMatch(cmd -> cmd.command.equals(command));
    }

    public boolean isPrint() {
        return this.isPrint;
    }
}
