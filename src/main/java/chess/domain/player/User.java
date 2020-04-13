package chess.domain.player;

import java.util.Objects;

public class User {

    public static final User EMPTY_BOARD_USER = new User("");

    private static final int MAXIMUM_LENGTH = 10;
    private final String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() > MAXIMUM_LENGTH) {
            throw new IllegalArgumentException("이름은 최대 10자입니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User)o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}