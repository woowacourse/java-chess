package chess.consolecontroller;

import chess.consolecontroller.handler.CommandHandler;
import chess.generic.VoidFunction;

import java.util.function.Supplier;

public class ChessController {

    public static void run(Supplier<String> commandInput, VoidFunction voidFunction) {
        voidFunction.doSomething();
        ChessContext chessContext = ChessContext.empty();

        while (chessContext.isNotEnd()) {
            String command = commandInput.get();
            CommandHandler.handle(chessContext, command);
        }
    }

}
