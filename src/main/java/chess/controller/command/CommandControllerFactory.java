package chess.controller.command;

import java.util.Arrays;
import java.util.function.Function;

public enum CommandControllerFactory {
    START("start", input -> new StartController()),
    MOVE("move", MoveController::new),
    STATUS("status", input -> new StatusController()),
    END("end", input -> new EndController()),
    EXIT("exit", input -> new ExitController());
    
    private final String command;
    private final Function<String, CommandController> controllerFunction;
    
    CommandControllerFactory(String command, Function<String, CommandController> controllerFunction) {
        this.command = command;
        this.controllerFunction = controllerFunction;
    }
    
    public static boolean hasCommand(String input) {
        return Arrays.stream(CommandControllerFactory.values())
                     .anyMatch(commandFactory -> commandFactory.command.equals(input));
    }
    
    public static CommandController createFrom(String input) {
        return findFunctionByInput(input).apply(input);
    }
    
    public static Function<String, CommandController> findFunctionByInput(String input) {
        return Arrays.stream(CommandControllerFactory.values())
                     .filter(commandFactory -> commandFactory.command.equals(input))
                     .map(commandFactory -> commandFactory.controllerFunction)
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("메뉴에 없는 커맨드입니다."));
    }
}
