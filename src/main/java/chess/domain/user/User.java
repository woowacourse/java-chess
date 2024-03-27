package chess.domain.user;

import java.util.regex.Pattern;

public class User {

    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z가-힣]+(?:\\s+[a-zA-Z가-힣]+)*$");
    private static final String INVALID_NAME_FORMAT = "사용자 이름은 5자 이하여야합니다.";

    private final Long id;
    private final String name;

    public User(final String name) {
        this(null, name);
    }

    public User(final Long id, final String name) {
        validate(name);
        this.id = id;
        this.name = name;
    }

    private void validate(final String name) {
        if (name.isEmpty() || name.length() > 5 || !PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_NAME_FORMAT);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
