package chess.controller.resolver;

import chess.controller.dto.DefaultRequest;

public interface RequestResolver {
    boolean support(String command);

    DefaultRequest<?> convert(String command);
}
