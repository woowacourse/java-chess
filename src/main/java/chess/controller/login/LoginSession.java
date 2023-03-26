package chess.controller.login;

public class LoginSession {

    private static String currentLoginId = null;

    private LoginSession() {
    }

    public static boolean isLoggedIn() {
        return currentLoginId != null;
    }

    public static void login(String userId) {
        currentLoginId = userId;
    }

    public static void logout() {
        currentLoginId = null;
    }

    public static String getCurrentLoginId() {
        return currentLoginId;
    }
}
