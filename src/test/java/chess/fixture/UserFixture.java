package chess.fixture;

import chess.domain.user.User;

public class UserFixture {
    private UserFixture() {
    }

    public static User createUserChoco() {
        return User.of(1L, "choco");
    }

    public static User createUserKhaki() {
        return User.of(2L, "khaki");
    }
}
