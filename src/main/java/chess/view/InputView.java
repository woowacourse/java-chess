package chess.view;

import chess.controller.main.Input;
import chess.controller.main.Request;
import chess.view.request.RequestImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView implements Input {

    private static final Scanner scanner = new Scanner(System.in);

    private final LoginImpl loginImpl;
    private final JoinBoardImpl joinBoard;

    public InputView(LoginImpl loginImpl, JoinBoardImpl joinBoard) {
        this.loginImpl = loginImpl;
        this.joinBoard = joinBoard;
    }

    @Override
    public Request inputGameCommand() {
        List<String> commands = Arrays.asList(scanner.nextLine().split(" "));
        return new RequestImpl(commands, loginImpl.getUserId(), joinBoard.getBoardId());
    }
}
