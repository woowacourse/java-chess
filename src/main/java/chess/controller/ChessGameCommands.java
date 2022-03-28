package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.End;
import chess.domain.command.Move;
import chess.domain.command.Start;
import chess.domain.command.Status;
import java.util.Arrays;
import java.util.function.Function;

public enum ChessGameCommands {

    START("start", Start::new),
    MOVE("move", Move::new),
    END("end", End::new),
    STATUS("status", Status::new),
    ;

    private static final String WRONG_COMMAND_MESSAGE = "잘못된 명령어를 입력하였습니다.";

    private final String value;
    private final Function<ChessGame, Command> function;

    ChessGameCommands(String value, Function<ChessGame, Command> function) {
        this.value = value;
        this.function = function;
    }

    public static ChessGameCommands from(final String inputCommand) {
        if (inputCommand.startsWith(MOVE.value)) {
            return MOVE;
        }
        return Arrays.stream(ChessGameCommands.values())
            .filter(it -> it.value.equalsIgnoreCase(inputCommand))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_MESSAGE));
    }

    public static Command of(final String inputCommand, ChessGame chessGame) {
        if (inputCommand.startsWith(MOVE.value)) {
            return new Move(chessGame);
        }
        return Arrays.stream(ChessGameCommands.values())
            .filter(it -> it.value.equalsIgnoreCase(inputCommand))
            .map(command -> command.function.apply(chessGame))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_MESSAGE));
    }

}
