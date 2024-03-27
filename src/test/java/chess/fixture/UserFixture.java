package chess.fixture;

import chess.domain.user.User;

public class UserFixture {
    private UserFixture() {
    }

    public static User createUserChoco() {
        return new User(1L, "choco");
    }

    public static User createUserKhaki() {
        return new User(2L, "khaki");
    }
}
