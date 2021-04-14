package chess.domain.game;

import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public enum Command {

    START("start", ((chessGame, input) -> {
        chessGame.start();
        OutputView.printChessBoard(chessGame.getChessBoard());
    })),
    END("end", ((chessGame, input) -> {
        chessGame.end();
    })),
    MOVE("move", (((chessGame, input) -> {
        chessGame.run(Command.parseCommand(input));
        OutputView.printChessBoard(chessGame.getChessBoard());
    }))),
    STATUS("status", (((chessGame, input) -> {
        Result result = chessGame.gameResult();
        OutputView.printResult(result);
    })));

    public static final String INVALID_COMMAND = "[ERROR] 올바른 명령어가 아닙니다.";
    private static final int COMMAND_INDEX = 0;

    private final String command;
    private final BiConsumer<ChessGame, String> commandAction;

    Command(String command,
        BiConsumer<ChessGame, String> commandAction) {
        this.command = command;
        this.commandAction = commandAction;
    }


    public static Command findCommand(String input) {
        return findCommand(parseCommand(input));
    }

    public static Command findCommand(List<String> input) {
        return Arrays.stream(values())
            .filter(value -> value.command.equals(input.get(COMMAND_INDEX)))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND));
    }

    public static List<String> parseCommand(String input) {
        return Arrays.stream(input.split("\\s"))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public void execute(ChessGame chessGame, String input) {
        commandAction.accept(chessGame, input);
    }
}
