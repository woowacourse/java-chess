package chess.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public List<String> inputCommand() {
        try {
            String input = SCANNER.nextLine();
            List<String> inputs = split(input);
            checkSize(inputs);
            return inputs;
        } catch (IllegalArgumentException error) {
            System.out.print(error.getMessage());
            return inputCommand();
        }
    }

    private List<String> split(String input) {
        List<String> inputs = List.of(input.split(" "));
        return inputs.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private void checkSize(List<String> input) {
        if (input.size() != 1 && input.size() != 3) {
            throw new IllegalArgumentException("[ERROR] 올바른 명렁어 양식이 아닙니다.");
        }
    }
}
