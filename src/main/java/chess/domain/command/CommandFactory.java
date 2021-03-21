package chess.domain.command;

import java.util.Arrays;
import java.util.function.Supplier;

public enum CommandFactory {
    START("start", StatusCommand::new),
    MOVE("move", MoveCommand::new),
    STATUS("status", StatusCommand::new),
    END("end", EndCommand::new),
    EXIT("exit", ExitCommand::new);
    
    private final String command;
    private final Supplier<Command> commandSupplier;
    
    CommandFactory(String command, Supplier<Command> commandSupplier) {
        this.command = command;
        this.commandSupplier = commandSupplier;
    }
    
    public static boolean hasCommand(String inputCommand) {
        return Arrays.stream(CommandFactory.values())
                     .anyMatch(commandFactory -> commandFactory.command.equals(inputCommand));
    }
    
    public static Command createFrom(String inputCommand) {
        return findSupplierByInput(inputCommand).get();
    }
    
    public static Supplier<Command> findSupplierByInput(String inputCommand) {
        return Arrays.stream(CommandFactory.values())
                .filter(commandFactory -> commandFactory.command.equals(inputCommand))
                .map(commandFactory -> commandFactory.commandSupplier)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("메뉴에 없는 커맨드입니다."));
    }
}
