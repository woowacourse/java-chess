package chess.controller.handler;

import chess.controller.ChessContext;
import chess.controller.command.ChessCommand;
import chess.controller.dto.DefaultRequest;
import chess.controller.resolver.MoveResolver;
import chess.controller.resolver.RequestResolver;
import chess.controller.resolver.VoidResolver;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

public enum CommandHandler {
    START(Pattern.compile("start"), new VoidResolver(), ChessCommand::start),
    MOVE(Pattern.compile("move ([a-h][1-8]) ([a-h][1-8])"), new MoveResolver(), ChessCommand::move),
    STATUS(Pattern.compile("status"), new VoidResolver(), ChessCommand::status),
    END(Pattern.compile("end"), new VoidResolver(), ChessCommand::end);

    private final Pattern pattern;
    private final RequestResolver requestResolver;
    private final BiConsumer<ChessContext, DefaultRequest<?>> action;

    CommandHandler(Pattern pattern, RequestResolver requestResolver, BiConsumer<ChessContext, DefaultRequest<?>> action) {
        this.pattern = pattern;
        this.requestResolver = requestResolver;
        this.action = action;
    }

    public static void handle(ChessContext chessContext, String command) {
        CommandHandler commandHandler = Arrays.stream(values())
                .filter(handler -> handler.findByCommand(command))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        DefaultRequest<?> convert = commandHandler.requestResolver.convert(command);

        commandHandler.action.accept(chessContext, convert);
    }

    private boolean findByCommand(String command) {
        return this.pattern.matcher(command).find();
    }
}
