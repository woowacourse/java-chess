package chess.controller.resolver;

import chess.controller.dto.DefaultRequest;
import chess.controller.dto.MoveRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveResolver implements RequestResolver {
    private static final Pattern MOVE_PATTERN = Pattern.compile("move ([a-h][1-8]) ([a-h][1-8])");

    @Override
    public DefaultRequest<? extends MoveRequest> convert(String command) {
        Matcher matcher = MOVE_PATTERN.matcher(command);
        if (!matcher.find()) {
            throw new IllegalArgumentException(command);
        }
        MoveRequest moveRequest = new MoveRequest(matcher.group(1), matcher.group(2));
        return new DefaultRequest<>(moveRequest);
    }
}
