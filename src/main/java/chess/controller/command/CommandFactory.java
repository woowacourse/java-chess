package chess.controller.command;

import java.util.Arrays;
import java.util.function.Supplier;

public enum CommandFactory {
    START(StartCommand::new),
    MOVE(MoveCommand::new),
    END(EndCommand::new);
    
    private final Supplier<Command> gameState;
    
    CommandFactory(Supplier<Command> gameState) {
        this.gameState = gameState;
    }
    
    public static Command creaeteCommand(String inputCommand) {
        return from(inputCommand).gameState.get();
    }
    
    private static CommandFactory from(String otherCommand) {
        return Arrays.stream(values())
                .filter(commandFactory -> commandFactory.name().equals(otherCommand.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다. 다시 입력해주세요."));
    }
}
