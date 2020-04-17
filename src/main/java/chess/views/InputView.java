package chess.views;

import chess.dto.RequestDto;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static RequestDto getCommand() {
        String string = scanner.nextLine();
        return new RequestDto(string);
    }
}
