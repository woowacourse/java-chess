package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String START = "start";
    private static final String END = "end";

    private InputView() {
    }

    public static void askStart() {
        System.out.println("체스 게임을 시작합니다");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");

        final String input = scanner.nextLine();
        if (!input.equals(START)) {
            throw new IllegalArgumentException("게임을 시작하려면 start를 입력해주세요");
        }
    }

    public static String askNext() {
        final String input = scanner.nextLine();

        if (!input.equals(END)) {
            throw new IllegalArgumentException("move 혹은 end만 입력해주세요");
        }
        return input;
    }
}
