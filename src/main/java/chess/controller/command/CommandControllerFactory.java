package chess.controller.command;

import java.util.Arrays;
import java.util.function.Supplier;

public enum CommandControllerFactory {
    START("start", StartController::new), MOVE("move", MoveController::new), STATUS("status", StatusController::new),
    END("end", EndController::new), EXIT("exit", ExitController::new);
    
    private final String command;
    private final Supplier<CommandController> controllerSupplier;
    
    CommandControllerFactory(String command, Supplier<CommandController> controllerSupplier) {
        this.command = command;
        this.controllerSupplier = controllerSupplier;
    }
    
    public static boolean hasCommand(String input) {
        return Arrays.stream(CommandControllerFactory.values())
                     .anyMatch(commandFactory -> commandFactory.command.equals(input));
    }
    
    public static CommandController createFrom(String input) {
        return findFunctionByInput(input).get();
    }
    
    public static Supplier<CommandController> findFunctionByInput(String input) {
        return Arrays.stream(CommandControllerFactory.values())
                     .filter(commandFactory -> commandFactory.command.equals(input))
                     .map(commandFactory -> commandFactory.controllerSupplier)
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("메뉴에 없는 커맨드입니다."));
    }
}
