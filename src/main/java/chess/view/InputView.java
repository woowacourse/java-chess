package chess.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final List<String> COMMANDS = List.of("start", "end");

    private static final Scanner scanner = new Scanner(System.in);

    public static void announceStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static String requestCommand() {
        String command = scanner.nextLine().trim();
        validateCommand(command);
        return command;
    }

    private static void validateCommand(String command) {
        if (!COMMANDS.contains(command)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다");
        }
    }
}
