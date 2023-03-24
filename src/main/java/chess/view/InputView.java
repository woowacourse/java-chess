package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public ChessRequest readGameCommand() {
        List<String> list = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());
        return new ChessRequest(list.get(0), list.subList(1, list.size()));
    }
}
