package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final static Scanner input = new Scanner(System.in);
    private final InputViewValidator inputViewValidator = new InputViewValidator();

    public List<String> readGameCommand() {
        String gameCommand = input.nextLine();
        inputViewValidator.validate(gameCommand);
        return Arrays.asList(gameCommand.split(" "));
    }
}
