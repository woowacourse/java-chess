package chess.console.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private InputView() {
        throw new AssertionError();
    }

    public static String requestGameCommand() {
        return Console.readLine();
    }
}
