package chess.controller.resolver;

import chess.controller.dto.DefaultRequest;

public interface RequestResolver {
    DefaultRequest<?> convert(String command);
}
