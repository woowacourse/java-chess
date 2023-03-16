package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readGameCommand() {
        return Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());
    }

    public boolean readStartCommand() {
        String input = scanner.nextLine();
        if (!"start".equalsIgnoreCase(input)) {
            throw new IllegalArgumentException("게임을 시작해주세요");
        }
        return true;
    }
}
