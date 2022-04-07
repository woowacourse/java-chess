package chess.domain.event;

import chess.domain.board.position.Position;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveCommand {

    private static final String DESCRIPTION_DELIMITER = " ";
    private static final String JSON_CONTENT_REGEX = "\\{\"source\":\"(.*)\",\"target\":\"(.*)\"}";
    private static final Pattern PATTERN = Pattern.compile(JSON_CONTENT_REGEX);
    private static final String INVALID_JSON_FORMAT_EXCEPTION_MESSAGE = "JSON 형식에 부합하지 않습니다.";

    private final Position source;
    private final Position target;

    public MoveCommand(String source, String target) {
        this.source = Position.of(source);
        this.target = Position.of(target);
    }

    public static MoveCommand ofEventDescription(String description) {
        String[] positions = description.split(DESCRIPTION_DELIMITER);
        return new MoveCommand(positions[0], positions[1]);
    }

    public static MoveCommand ofJson(String json) {
        Matcher matcher = toValidMatcher(json);
        String source = matcher.group(1);
        String target = matcher.group(2);
        return new MoveCommand(source, target);
    }

    private static Matcher toValidMatcher(String json) {
        Matcher matcher = PATTERN.matcher(json);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_JSON_FORMAT_EXCEPTION_MESSAGE);
        }
        return matcher;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }

    public String toDescription() {
        String sourceKey = source.toKey();
        String targetKey = target.toKey();

        return sourceKey + DESCRIPTION_DELIMITER + targetKey;
    }
}
