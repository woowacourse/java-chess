package view;

import java.util.Scanner;

public class InputView {
    private InputView() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static ChessCommand inputChessCommand() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return ChessCommand.from(scanner.nextLine());
    }

}
