package chess.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String DELIMITER = " ";
    public static final int LIMIT = -1;

    public static List<String> readCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 점수 계산 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        return Arrays.stream(scanner.nextLine().split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }
}
