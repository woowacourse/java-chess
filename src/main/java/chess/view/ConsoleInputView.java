package chess.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    @Override
    public boolean askChessRun() {
        try {
            System.out.println("체스 게임을 시작합니다.");
            System.out.println("게임 시작은 start, 종료는 end 명령어를 입력하세요.");
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
}
