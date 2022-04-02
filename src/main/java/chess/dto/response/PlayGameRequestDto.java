package chess.dto.response;

import chess.dto.MoveCommandDto;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayGameRequestDto {

    private static final String JSON_CONTENT_REGEX = "\\{"
            + "\"source\":\"(.*)\","
            + "\"target\":\"(.*)\"}";
    private static final Pattern PATTERN = Pattern.compile(JSON_CONTENT_REGEX);
    private static final String INVALID_JSON_FORMAT = "JSON 형식에 부합하지 않습니다.";

    private final int gameId;
    private final MoveCommandDto moveCommand;

    public PlayGameRequestDto(String param, String body) {
        this.gameId = Integer.parseInt(param);
        this.moveCommand = toMoveCommand(body);
    }

    private MoveCommandDto toMoveCommand(String body) {
        Matcher matcher = toValidMatcher(body);
        String source = matcher.group(1);
        String target = matcher.group(2);
        return new MoveCommandDto(source, target);
    }

    private static Matcher toValidMatcher(String json) {
        Matcher matcher = PATTERN.matcher(json);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_JSON_FORMAT);
        }
        return matcher;
    }

    public int getGameId() {
        return gameId;
    }

    public MoveCommandDto getMoveCommand() {
        return moveCommand;
    }
}
