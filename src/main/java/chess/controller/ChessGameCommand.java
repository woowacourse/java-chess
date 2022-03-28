package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.End;
import chess.domain.command.Move;
import chess.domain.command.Start;
import chess.domain.command.Status;
import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Pattern;

public enum ChessGameCommand {

    START("start", Start::new),
    MOVE("move [a-h][1-9] [a-h][1-9]", Move::new),
    END("end", End::new),
    STATUS("status", Status::new),
    ;

    private static final String WRONG_COMMAND_MESSAGE = "잘못된 명령어를 입력하였습니다.";

    private final String value;
    private final Function<ChessGame, Command> function;

    ChessGameCommand(String value, Function<ChessGame, Command> function) {
        this.value = value;
        this.function = function;
    }

    public static Command of(final String inputCommand, ChessGame chessGame) {
        if (inputCommand.startsWith(MOVE.value)) {
            return new Move(chessGame);
        }
        return Arrays.stream(ChessGameCommand.values())
            .filter(it -> Pattern.matches(it.value, inputCommand))
            .map(command -> command.function.apply(chessGame))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_MESSAGE));
    }

}
