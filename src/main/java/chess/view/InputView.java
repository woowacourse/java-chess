package chess.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private InputView() {
        throw new AssertionError();
    }

    public static Command requestGameCommand() {
        return Command.from(Console.readLine());
    }
}
