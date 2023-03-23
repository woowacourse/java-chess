package chess.domain.game;

import java.util.ArrayList;
import java.util.List;

public class GameSession {

    private static final List<Game> runningGame = new ArrayList<>();
    private static final int NO_BOARD_EXIST = 0;
    private static final int DEFAULT_BOARD_INDEX = 0;

    private GameSession() {
    }

    public static void makeSession(Game game) {
        runningGame.add(game);
    }

    public static void replaceSession(Game game) {
        runningGame.add(DEFAULT_BOARD_INDEX, game);
    }

    public static Game getGame() {
        if (runningGame.size() == NO_BOARD_EXIST) {
            throw new IllegalStateException();
        }
        return runningGame.get(DEFAULT_BOARD_INDEX);
    }

    public static boolean existGame() {
        return runningGame.size() != NO_BOARD_EXIST;
    }

    public static void clear() {
        runningGame.clear();
    }
}
