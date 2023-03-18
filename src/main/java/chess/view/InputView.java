package chess.view;

import chess.view.request.RequestInfo;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public RequestInfo inputGameCommand() {
        return new RequestInfo(scanner.nextLine());
    }
}
