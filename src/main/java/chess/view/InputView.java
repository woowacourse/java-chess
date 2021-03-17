package chess.view;

import chess.domain.Command;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        return SCANNER.nextLine();
    }

    public static Command inputCommandV2() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");

        String commandLine = SCANNER.nextLine();
        List<String> commandAndParameters = Arrays.asList(commandLine.split(" "));
        String command = commandAndParameters.get(0);
        List<String> parameters = commandAndParameters.subList(1, commandAndParameters.size());

        return Command.of(SCANNER.nextLine());
    }
}
