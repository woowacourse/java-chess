package chess.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = " ";
    private static final int LIMIT = -1;
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String readMainCommand(final String user, final String room) {
        printStatus(user, room);
        System.out.println("> 계정 관리 : user");
        System.out.println("> 게임 관리 : room");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 종료 : end");

        return SCANNER.nextLine();
    }

    private static void printStatus(final String user, final String room) {
        System.out.println("[로그인 한 유저: " + user + ", 선택한 방 이름: " + room + "]");
    }

    public static List<String> readUserCommand(final String user, final String room) {
        printStatus(user, room);
        System.out.println("> 회원가입 : register 이름 - 예) register charlie");
        System.out.println("> 로그인 : login 이름 - 예) login charlie");
        System.out.println("> 로그아웃 : logout");
        System.out.println("> 종료 : end");

        final String input = SCANNER.nextLine();

        return Arrays.stream(input.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }

    public static List<String> readCommand() {
        final String input = SCANNER.nextLine();

        return Arrays.stream(input.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }
}
