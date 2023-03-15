package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public boolean readStartCommend() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        String input = scanner.nextLine();
        return isStart(input);
    }

    private boolean isStart(String input) {
        if ("start".equalsIgnoreCase(input)) {
            return true;
        }
        if ("end".equalsIgnoreCase(input)) {
            return false;
        }
        throw new IllegalArgumentException("올바른 명령어를 입력해주세요.");
    }

}
