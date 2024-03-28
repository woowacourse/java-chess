package chess.domain.room;

import java.util.regex.Pattern;

public class Room {
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z가-힣0-9]+(?:\\s+[a-zA-Z가-힣0-9]+)*$");
    private static final String INVALID_NAME_FORMAT = "체스방 이름은 10자 이하로 입력해주세요.";
    private static final int MAX_ROOM_NAME_LENGTH = 10;

    private final Long id;
    private final long userId;
    private final String name;

    public Room(final long userId, final String name) {
        this(null, userId, name);
    }

    public Room(final Long id, final long userId, final String name) {
        validate(name);
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    private void validate(final String name) {
        if (name.isEmpty() || name.length() > MAX_ROOM_NAME_LENGTH || !PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_NAME_FORMAT);
        }
    }

    public Long getRoomId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
