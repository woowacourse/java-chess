package chess.controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public enum ChessGameCommand {

    START("start"),
    END("end"),
    MOVE("move");

    private final String input;

    ChessGameCommand(final String input) {
        this.input = input;
    }

    public static ChessGameCommand generateExecuteCommand(final String input) {
        List<ChessGameCommand> gameExecuteCommands = List.of(START, END);
        return gameExecuteCommands.stream()
                .filter(chessGameCommand -> chessGameCommand.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format(
                        "%s 중 입력해야 합니다.",
                        gameExecuteCommands.stream().map(chessGameCommand -> chessGameCommand.input).collect(Collectors.joining(", ")))
                ));
    }

    public static ChessGameCommand generateMoveCommand(final String input) {
        List<ChessGameCommand> gameMoveCommands = List.of(MOVE, END);
        return gameMoveCommands.stream()
                .filter(chessGameCommand -> input.contains(chessGameCommand.input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format(
                        "%s 중 입력해야 합니다.",
                        gameMoveCommands.stream().map(chessGameCommand -> chessGameCommand.input).collect(Collectors.joining(", ")))
                ));
    }

    @Override
    public String toString() {
        return input;
    }
}
