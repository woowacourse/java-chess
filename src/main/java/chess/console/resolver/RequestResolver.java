package chess.console.resolver;

import chess.console.dto.DefaultRequest;

public interface RequestResolver {
    boolean support(String command);

    DefaultRequest<?> convert(String command);
}
