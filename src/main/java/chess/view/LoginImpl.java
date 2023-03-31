package chess.view;

import chess.controller.user.Login;
import java.util.Optional;

public class LoginImpl implements Login {

    private Optional<Integer> userId = Optional.empty();

    @Override
    public void login(int userId) {
        this.userId = Optional.of(userId);
    }

    public Optional<Integer> getUserId() {
        return userId;
    }
}
