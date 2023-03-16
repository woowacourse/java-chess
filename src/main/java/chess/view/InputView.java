package chess.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<String> buffer;

    private InputView() {
    }

    public static Command readCommand() {
        final List<String> inputs = Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList());
        final Command command = Command.from(inputs.remove(0));

        buffer = inputs;
        return command;
    }

    public static List<String> getCoordinates() {
        return Collections.unmodifiableList(buffer);
    }
}

