package chess.consolecontroller.resolver;

import chess.consolecontroller.dto.DefaultRequest;

public interface RequestResolver {
    boolean support(String command);

    DefaultRequest<?> convert(String command);
}
