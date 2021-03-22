package chess.controller;

import chess.domain.Command2;
import chess.domain.CommandAsString;
import java.util.EnumMap;
import java.util.Map;

public abstract class ChessControllerOld2 implements Controller {

    private final Map<Command2, Controller> childControllers = new EnumMap<>(Command2.class);

    protected void addController(Command2 command, Controller controller) {
        childControllers.put(command, controller);
    }

    protected void runNextController(CommandAsString command) {
        Controller nextController = childControllers.get(command.command());
        nextController.run();
    }
}
