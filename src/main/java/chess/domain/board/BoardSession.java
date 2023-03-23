package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

public class BoardSession {
    private static final List<Board> runningBoards = new ArrayList<>();
    private static final int NO_BOARD_EXIST = 0;
    private static final int DEFAULT_BOARD_INDEX = 0;

    private BoardSession() {
    }

    public static void makeSession(Board board) {
        runningBoards.add(board);
    }

    public static Board getBoard() {
        if (runningBoards.size() == NO_BOARD_EXIST) {
            throw new IllegalStateException();
        }
        return runningBoards.get(DEFAULT_BOARD_INDEX);
    }

    public static boolean existBoard() {
        return runningBoards.size() != NO_BOARD_EXIST;
    }

    public static void clear() {
        runningBoards.clear();
    }
}
