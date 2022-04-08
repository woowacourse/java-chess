package chess.domain.command;

import chess.domain.board.position.Position;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveRoute {

    private static final String DESCRIPTION_DELIMITER = " ";
    private static final String JSON_CONTENT_REGEX = "\\{\"source\":\"(.*)\",\"target\":\"(.*)\"}";
    private static final Pattern PATTERN = Pattern.compile(JSON_CONTENT_REGEX);
    private static final String INVALID_JSON_FORMAT_EXCEPTION_MESSAGE = "JSON 형식에 부합하지 않습니다.";

    private final Position source;
    private final Position target;

    public MoveRoute(String source, String target) {
        this.source = Position.of(source);
        this.target = Position.of(target);
    }

    public static MoveRoute ofEventDescription(String description) {
        String[] positions = description.split(DESCRIPTION_DELIMITER);
        return new MoveRoute(positions[0], positions[1]);
    }

    public static MoveRoute ofJson(String json) {
        Matcher matcher = toValidMatcher(json);
        String source = matcher.group(1);
        String target = matcher.group(2);
        return new MoveRoute(source, target);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoveRoute that = (MoveRoute) o;
        return Objects.equals(source, that.source)
                && Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public String toString() {
        return "MoveCommand{" + "source=" + source + ", target=" + target + '}';
    }
}
