package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class InputView {

    private static final String MOVE_DELIMITER = " ";
    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readCommands() {
        return Arrays.asList(scanner.nextLine().split(MOVE_DELIMITER));
    }

    public String readLoadCommand() {
        System.out.println("존재하는 게임이 있습니다. 이어 하시겠습니까? (예는 y, 아니오는 n)");
        Pattern compile = Pattern.compile("^[yn]$");

        String input = scanner.nextLine();
        if (!compile.matcher(input).matches()) {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요");
        }

        return input;
    }
}
