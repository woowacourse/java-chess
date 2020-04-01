package chess.consolecontroller.resolver;

import chess.consolecontroller.dto.DefaultRequest;
import chess.consolecontroller.dto.MoveRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveResolver implements RequestResolver {
    private static final Pattern MOVE_PATTERN = Pattern.compile("move ([a-h][1-8]) ([a-h][1-8])");

    @Override
    public boolean support(String command) {
        return MOVE_PATTERN.matcher(command).find();
    }

    @Override
    public DefaultRequest<? extends MoveRequest> convert(String command) {
        Matcher matcher = MOVE_PATTERN.matcher(command);

        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("%s : 올바르지 않은 입력입니다. move () ()로 입력해주세요. ", command));
        }
        MoveRequest moveRequest = new MoveRequest(matcher.group(1), matcher.group(2));
        return new DefaultRequest<>(moveRequest);
    }
}
