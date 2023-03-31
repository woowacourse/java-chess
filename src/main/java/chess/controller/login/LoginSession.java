package chess.controller.login;

public class LoginSession {

    private static String currentLoginId = null;
    private static String playingRoomName = null;

    private LoginSession() {
    }

    public static boolean isLoggedIn() {
        return currentLoginId != null;
    }

    public static void login(String userId) {
        currentLoginId = userId;
    }

    public static void enterRoom(String roomName) {
        playingRoomName = roomName;
    }

    public static String getCurrentLoginId() {
        return currentLoginId;
    }

    public static String getCurrentPlayingRoomName() {
        if (playingRoomName == null) {
            throw new IllegalStateException("현재 진행 중인 게임이 없습니다.");
        }
        return playingRoomName;
    }

    public static boolean havePlayingGame() {
        return playingRoomName != null;
    }
}
