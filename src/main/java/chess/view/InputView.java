package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readGameStartCommand() {
        System.out.println("체스 게임을 시작합니다.");
        final String rawInput = read("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        validateCommand(rawInput);
        return rawInput;
    }

    private void validateCommand(final String rawInput) {
        if (!("start".equals(rawInput) || "end".equals(rawInput))) {
            throw new IllegalArgumentException("[ERROR] start 또는 end만 입력할 수 있습니다.");
        }
    }

    private String read(final String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
