package chess.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String askChessRun() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        return askGameCommand();
    }

    @Override
    public String askGameCommand() {
        return SCANNER.nextLine();
    }
}
