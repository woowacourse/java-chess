package chess.controller.resolver;

import chess.controller.dto.DefaultRequest;

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
