package chess.controller;

import java.util.Arrays;
import java.util.function.UnaryOperator;

import chess.domain.chess.Chess;

public enum Command {
    START("start", ConsoleChessApplication::start),
    MOVE("move", ConsoleChessApplication::move),
    STATUS("status", ConsoleChessApplication::status),
    END("end", ConsoleChessApplication::end),
    EXIT("exit", ConsoleChessApplication::exit);

    private static final String ERROR_COMMAND_CANNOT_FIND = "메뉴에 없는 커맨드입니다. 다시 입력해주세요.";

    private final String command;
    private final UnaryOperator<Chess> chessUnaryOperator;

    Command(String command, UnaryOperator<Chess> chessUnaryOperator) {
        this.command = command;
        this.chessUnaryOperator = chessUnaryOperator;
    }

    public static Command findCommandByInputCommand(String inputCommand) {
        return Arrays.stream(Command.values())
                     .filter(commandMenu -> commandMenu.command.equals(inputCommand))
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException(ERROR_COMMAND_CANNOT_FIND));
    }

    public Chess execute(Chess chess) {
        return chessUnaryOperator.apply(chess);
    }
}
