package chess.consolecontroller.resolver;

import chess.consolecontroller.dto.DefaultRequest;

public class VoidResolver implements RequestResolver {
    @Override
    public boolean support(String command) {
        return true;
    }

    @Override
    public DefaultRequest<?> convert(String command) {
        return DefaultRequest.empty();
    }
}
