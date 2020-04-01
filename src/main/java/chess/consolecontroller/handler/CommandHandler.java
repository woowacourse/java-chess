package chess.consolecontroller.handler;

import chess.consolecontroller.ChessContext;
import chess.consolecontroller.command.ChessCommand;
import chess.consolecontroller.dto.DefaultRequest;
import chess.consolecontroller.resolver.RequestResolverGroup;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

public enum CommandHandler {
    START(Pattern.compile("start"), ChessCommand::start),
    MOVE(Pattern.compile("move ([a-h][1-8]) ([a-h][1-8])"), ChessCommand::move),
    STATUS(Pattern.compile("status"), ChessCommand::status),
    END(Pattern.compile("end"), ChessCommand::end);

    private final Pattern pattern;
    private final BiConsumer<ChessContext, DefaultRequest<?>> action;

    CommandHandler(Pattern pattern, BiConsumer<ChessContext, DefaultRequest<?>> action) {
        this.pattern = pattern;
        this.action = action;
    }

    public static void handle(ChessContext chessContext, String command) {
        CommandHandler commandHandler = findHandlerByCommand(command);

        DefaultRequest<?> convert = RequestResolverGroup.resolve(command);

        commandHandler.action.accept(chessContext, convert);
    }

    private static CommandHandler findHandlerByCommand(String command) {
        return Arrays.stream(values())
                .filter(handler -> handler.findByCommand(command))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean findByCommand(String command) {
        return this.pattern.matcher(command).find();
    }
}
