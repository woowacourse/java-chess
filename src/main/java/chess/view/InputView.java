package chess.view;

import chess.domain.command.Commands;
import java.util.Arrays;
import java.util.Scanner;

public final class InputView {

    private static final String COMMANDS_DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public static void announceStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static Commands requestCommands() {
        String commandInput = scanner.nextLine().trim();
        return new Commands(Arrays.asList(commandInput.split(COMMANDS_DELIMITER)));
    }
}
