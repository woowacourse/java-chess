package chess.game;

import java.util.Objects;
import java.util.regex.Pattern;

public class GameId {
    private static final Pattern GAME_ID_REGEX = Pattern.compile("^[1-9]\\d{0,3}$");
    private static final String INVALID_GAME_ID_EXCEPTION_MESSAGE = "[ERROR] 방번호는 1~9999 까지 숫자만 가능합니다.";

    private final String gameId;

    public GameId(String gameId) {
        validateGameId(gameId);
        this.gameId = gameId;
    }

    private void validateGameId(String gameId) {
        if (!GAME_ID_REGEX.matcher(gameId).matches()) {
            throw new IllegalArgumentException(INVALID_GAME_ID_EXCEPTION_MESSAGE);
        }
    }

    public String getGameId() {
        return gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameId)) {
            return false;
        }
        GameId gameId1 = (GameId) o;
        return Objects.equals(gameId, gameId1.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }
}

