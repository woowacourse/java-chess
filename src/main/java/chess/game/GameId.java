package chess.game;

import java.util.regex.Pattern;

public class GameId {
    private static final Pattern GAME_ID_REGEX = Pattern.compile("^[1-9]\\d{0,4}$");

    private final String gameId;

    public GameId(String gameId) {
        validateGameId(gameId);
        this.gameId = gameId;
    }

    private void validateGameId(String gameId) {
        if (!GAME_ID_REGEX.matcher(gameId).matches()) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식의 체스방 ID 입니다.");
        }
    }

    public String getGameId() {
        return gameId;
    }
}

