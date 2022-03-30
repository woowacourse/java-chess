package chess.view;

import chess.domain.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMEND_DELIMITER = " ";
    private static final int COMMEND_WORD_INDEX = 0;

    private static final Scanner scanner = new Scanner(System.in);

    public static void announceStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static List<String> requestCommand() {
        String commandInput = scanner.nextLine().trim();
        List<String> commands = Arrays.asList(commandInput.split(COMMEND_DELIMITER));
        validateCommand(commands.get(COMMEND_WORD_INDEX));
        return commands;
    }

    private static void validateCommand(String command) {
        if (!Command.words().contains(command)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다");
        }
    }
}
