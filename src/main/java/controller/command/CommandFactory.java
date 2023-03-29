package controller.command;

import service.ChessService;

import java.util.Arrays;
import java.util.function.Function;

public enum CommandFactory {

    NEW(NewGame::new),
    LOAD(LoadGame::new),
    MOVE(Move::new),
    STATUS(Status::new),
    END(End::new);

    private final Function<ChessService, Command> gameState;

    CommandFactory(final Function<ChessService, Command> gameState) {
        this.gameState = gameState;
    }

    public static Command createCommand(final String input, ChessService chessService) {
        return from(input).gameState.apply(chessService);
    }

    private static CommandFactory from(final String input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(input.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("안내된 명령어만 입력해주세요"));
    }

}
