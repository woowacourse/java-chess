package chess.view;

import chess.machine.Command;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static void announceStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static String requestCommand() {
        final String command = scanner.nextLine().trim();
        final String firstWord = command.split(" ")[0];
        validateCommand(firstWord);
        return command;
    }

    private static void validateCommand(final String command) {
        if (!Command.words().contains(command)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다");
        }
    }
}
