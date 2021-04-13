package chess.domain.game;

import java.util.Objects;

public class Game {

    private final String roomName;
    private final String whiteUsername;
    private final String blackUsername;

    private Game(final String roomName, final String whiteUsername, final String blackUsername) {
        this.roomName = roomName;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
    }

    public static Game of(final String roomName, final String whiteUsername, final String blackUsername) {
        validateGame(roomName, whiteUsername, blackUsername);
        return new Game(roomName, whiteUsername, blackUsername);
    }

    private static void validateGame(final String roomName, final String whiteUsername, final String blackUsername) {
        validateNull(roomName, whiteUsername, blackUsername);
        validateEmpty(roomName, whiteUsername, blackUsername);
    }

    private static void validateNull(final String roomName, final String whiteUsername, final String blackUsername) {
        Objects.requireNonNull(roomName, "방이름은 null 일 수 없습니다.");
        Objects.requireNonNull(whiteUsername, "유저 이름은 null 일 수 없습니다.");
        Objects.requireNonNull(blackUsername, "유저 이름은 null 일 수 없습니다.");
    }

    private static void validateEmpty(final String roomName, final String whiteUsername, final String blackUsername) {
        if (roomName.isEmpty()) {
            throw new IllegalArgumentException("방 이름은 1글자 이상 작성해야합니다.");
        }
        if (whiteUsername.isEmpty() || blackUsername.isEmpty()) {
            throw new IllegalArgumentException("유저 이름은 1글자 이상 작성해야합니다.");
        }
    }

    public String roomName() {
        return this.roomName;
    }

    public String whiteUsername() {
        return this.whiteUsername;
    }

    public String blackUsername() {
        return this.blackUsername;
    }
}
