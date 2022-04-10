package chess.domain.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandType {

    INIT(Pattern.compile("start")),
    MOVE(Pattern.compile("(move)\\s(..)\\s(..)")),
    STATUS(Pattern.compile("status")),
    END(Pattern.compile("end"));

    private static final String INVALID_MOVE_COMMAND_FORMAT_EXCEPTION_MESSAGE = "move xx xx 형식에 부합하지 않습니다.";

    private final Pattern pattern;

    CommandType(Pattern pattern) {
        this.pattern = pattern;
    }

    public boolean isValidDescription(String description) {
        Matcher matcher = pattern.matcher(description);
        return matcher.matches();
    }

    public MoveRoute toMoveRoute(String description) {
        Matcher matcher = toValidMatcher(description);
        String source = matcher.group(2);
        String target = matcher.group(3);
        return new MoveRoute(source, target);
    }

    private Matcher toValidMatcher(String description) {
        Matcher matcher = MOVE.pattern.matcher(description);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_MOVE_COMMAND_FORMAT_EXCEPTION_MESSAGE);
        }
        return matcher;
    }
}
