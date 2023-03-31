package chess.controller.game.games;

import chess.controller.exception.LoginException;
import chess.controller.main.Request;
import java.util.Optional;

public class GamesRequest {

    private final int userId;

    private GamesRequest(int userId) {
        this.userId = userId;
    }

    public static GamesRequest from(Request request) {
        Optional<Integer> userId = request.getUserId();
        if (userId.isEmpty()) {
            throw new LoginException();
        }
        return new GamesRequest(userId.get());
    }

    int getUserId() {
        return userId;
    }
}
