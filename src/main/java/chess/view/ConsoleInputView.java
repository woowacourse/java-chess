package chess.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    @Override
    public boolean askChessRun() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        try {
            String input = SCANNER.nextLine();
            validateCommand(input);
            return runFlag(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askChessRun();
        }
    }

    private void validateCommand(String input) {
        if (!input.equals(START_COMMAND) && !input.equals(END_COMMAND)) {
            throw new IllegalArgumentException("start 또는 end 를 입력해주세요.");
        }
    }

    private boolean runFlag(String input) {
        if (input.equals(START_COMMAND)) {
            return true;
        }
        return false;
    }

    @Override
    public String askMove() {
        try {
            String input = SCANNER.nextLine();
            validateMove(input);
            return input;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askMove();
        }
    }

    private static void validateMove(String input) {
        if (!input.contains("move")) {
            throw new IllegalArgumentException("잘못된 명령어를 입력하였습니다.");
        }
    }
}
