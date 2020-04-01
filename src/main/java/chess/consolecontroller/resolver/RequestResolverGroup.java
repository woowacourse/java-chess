package chess.consolecontroller.resolver;

import chess.consolecontroller.dto.DefaultRequest;

import java.util.Arrays;

public enum RequestResolverGroup {
    MOVE(new MoveResolver()),
    VOID(new VoidResolver());

    private final RequestResolver requestResolver;

    RequestResolverGroup(RequestResolver requestResolver) {
        this.requestResolver = requestResolver;
    }

    public static DefaultRequest<?> resolve(String command) {
        return Arrays.stream(values())
                .filter(resolver -> resolver.support(command))
                .findFirst()
                .orElse(RequestResolverGroup.VOID)
                .requestResolver.convert(command);
    }

    private boolean support(String command) {
        return this.requestResolver.support(command);
    }
}
