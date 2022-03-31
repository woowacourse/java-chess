package chess.domain;

import chess.domain.gamecommand.Command;
import chess.domain.gamecommand.End;
import chess.domain.gamecommand.Move;
import chess.domain.gamecommand.Start;
import chess.domain.gamecommand.Status;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;

public enum ChessGameCommand {

    START("start", Start::new),
    MOVE("move [a-h][1-9] [a-h][1-9]", Move::new),
    END("end", End::new),
    STATUS("status", Status::new),
    ;

    private static final String NOT_ALLOW_EMPTY = "빈값을 입력할 수 없습니다.";
    private static final String WRONG_COMMAND_MESSAGE = "잘못된 명령어를 입력하였습니다.";

    private final String value;
    private final Function<ChessGame, Command> function;

    ChessGameCommand(String value, Function<ChessGame, Command> function) {
        this.value = value;
        this.function = function;
    }

    public static Command of(final String inputCommand, ChessGame chessGame) {
        checkNullOrEmpty(inputCommand);
        return Arrays.stream(ChessGameCommand.values())
            .filter(it -> Pattern.matches(it.value, inputCommand))
            .map(command -> command.function.apply(chessGame))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_MESSAGE));
    }

    private static void checkNullOrEmpty(final String inputCommand) {
        Objects.requireNonNull(inputCommand, NOT_ALLOW_EMPTY);
        if (inputCommand.trim().isEmpty()) {
            throw new IllegalArgumentException(NOT_ALLOW_EMPTY);
        }
    }
}
