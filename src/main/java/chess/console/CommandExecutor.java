package chess.console;

import java.util.Arrays;
import java.util.List;

import chess.domain.game.ChessGame;

public enum CommandExecutor {

    START("START", 0,
            (chessApplication, chessGame, commandOptions) -> chessApplication.executeStart(chessGame)),

    MOVE("MOVE", 2,
            ChessApplication::executeMove),

    STATUS("STATUS", 0,
            (chessApplication, chessGame, commandOptions) -> chessApplication.executeStatus(chessGame)),

    END("END", 0,
            (chessApplication, chessGame, commandOptions) -> chessApplication.executeEnd(chessGame)),
    ;

    private final String command;
    private final int optionCount;
    private final ChessApplicationExecution chessApplicationExecution;

    CommandExecutor(final String command,
                    final int optionCount,
                    final ChessApplicationExecution chessApplicationExecution) {
        this.command = command;
        this.optionCount = optionCount;
        this.chessApplicationExecution = chessApplicationExecution;
    }

    public static CommandExecutor from(final String command) {
        return Arrays.stream(values())
                .filter(it -> command.equalsIgnoreCase(it.command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("명령어를 잘못 입력하셨습니다."));
    }

    public boolean equalsOptionCount(final int optionCount) {
        return this.optionCount == optionCount;
    }

    public void execute(final ChessApplication chessApplication,
                        final ChessGame chessGame,
                        final List<String> commandOptions) {
        chessApplicationExecution.execute(chessApplication, chessGame, commandOptions);
    }

    @FunctionalInterface
    private interface ChessApplicationExecution {
        void execute(final ChessApplication chessApplication,
                     final ChessGame chessGame,
                     final List<String> commandOptions);
    }
}
