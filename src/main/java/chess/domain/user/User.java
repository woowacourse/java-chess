package chess.domain.user;

public class User {
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 20;

    private final String name;

    private User(final String name) {
        this.name = name;
    }

    public static User create(final String name) {
        validateLength(name);
        return new User(name);
    }

    private static void validateLength(final String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 %d~%d글자 사이여야 합니다.", MIN_LENGTH, MAX_LENGTH));
        }
    }

    public String getName() {
        return name;
    }
}
