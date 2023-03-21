package chess.view;

import chess.controller.CommandRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static CommandRequest readRequest() {
        final String input = scanner.nextLine();
        final List<String> request = Arrays.stream(input.split(" ", -1))
                .map(String::trim)
                .collect(Collectors.toList());
        return CommandRequest.from(request);
    }
}
