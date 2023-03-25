package chess.controller.session;

public class UserSession {

    private static UserSession INSTANCE = new UserSession();

    private ThreadLocal<User> session = new ThreadLocal<>();

    private UserSession() {
    }

    public static UserSession getInstance() {
        return INSTANCE;
    }

    public void add(final User auth) {
        session.set(auth);
    }

    public int getId() {
        final User auth = session.get();
        if (auth == null) {
            return 0;
        }
        return session.get().getId();
    }

    public String getName() {
        final User auth = session.get();
        if (auth == null) {
            return "anonymous";
        }
        return auth.getName();
    }

    public void remove() {
        session.remove();
    }
}
