package chess.view;

import chess.controller.request.Input;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView implements Input {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public List<String> inputGameCommand() {
        return Arrays.asList(scanner.nextLine().split(" ", -1));
    }
}
