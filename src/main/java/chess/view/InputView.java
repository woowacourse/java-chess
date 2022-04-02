package chess.view;

import chess.domain.command.Commands;
import java.util.Arrays;
import java.util.Scanner;

public final class InputView {

    private static final String COMMANDS_DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public static void announceStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start" +
                        "\n> 게임 종료 : end" +
                        "\n> 말 이동 : move source위치 target위치 - 예. move a2 a3" +
                        "\n> 진행 상황 출력 : status");
    }

    public static Commands requestCommands() {
        String commandInput = scanner.nextLine().trim();
        return new Commands(Arrays.asList(commandInput.split(COMMANDS_DELIMITER)));
    }
}
