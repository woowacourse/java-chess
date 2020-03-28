package chess.controller.resolver;

import chess.controller.dto.DefaultRequest;

public class VoidResolver implements RequestResolver {
    @Override
    public DefaultRequest<?> convert(String command) {
        return DefaultRequest.empty();
    }
}
