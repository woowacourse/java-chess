package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public boolean readStartCommand() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        String command = scanner.nextLine();
        if (command.equals("start")) {
            return true;
        }

        if (command.equals("end")) {
            return false;
        }

        throw new IllegalArgumentException();
    }
}
