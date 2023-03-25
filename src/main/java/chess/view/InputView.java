package chess.view;

import chess.view.request.Request;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final int INITIAL_BOARD_ID = -1;
    private static final String INITIAL_USER_ID = null;
    private static final Scanner scanner = new Scanner(System.in);

    private int boardId = INITIAL_BOARD_ID;
    private String userId = INITIAL_USER_ID;

    public Request inputGameCommand() {
        List<String> commands = Arrays.asList(scanner.nextLine().split(" ", -1));
        return new Request(commands, boardId, userId);
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
