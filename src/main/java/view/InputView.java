package view;

import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public String getStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        String input = scanner.nextLine();
        checkBlank(input);
        return input;
    }

    public String getGameCommand() {
        String input = scanner.nextLine();
        checkBlank(input);
        return input;
    }

    private void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력이 잘못 되었습니다. 다시 입력해 주세요.");
        }
    }
}
