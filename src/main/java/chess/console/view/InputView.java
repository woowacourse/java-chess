package chess.console.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputView {
    private InputView() {
    }

    public static List<String> scanCommand() {
        final String input = Console.scan().trim();
        
        return Arrays.stream(input.split(" "))
                .collect(Collectors.toList());
    }
}
