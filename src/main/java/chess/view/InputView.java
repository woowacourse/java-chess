package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readStartCommand() {
        System.out.println("체스 게임을 시작합니다." + System.lineSeparator() + "게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return scanner.nextLine();
    }

    public String readEnd() {
        return scanner.nextLine();
    }

    public List<String> readMoveCommand() {
        String input = scanner.nextLine();
        return Arrays.asList(input.split(" ")).subList(1, 3);
    }
}
