package chess.controller.handler;

import chess.controller.ChessContext;
import chess.controller.command.ChessCommand;
import chess.controller.dto.DefaultRequest;
import chess.controller.resolver.RequestResolverGroup;

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
        CommandHandler commandHandler = Arrays.stream(values())
                .filter(handler -> handler.findByCommand(command))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        DefaultRequest<?> convert = RequestResolverGroup.resolve(command);

        commandHandler.action.accept(chessContext, convert);
    }

    private boolean findByCommand(String command) {
        return this.pattern.matcher(command).find();
    }
}
