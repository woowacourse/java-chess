package chess.view.input;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameInputView {
    private static final String DELIMITER = " ";
    private static final int LIMIT = -1;

    private final Scanner scanner;

    public GameInputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readCommand(final String user, final String room) {
        System.out.println("[로그인 한 유저: " + user + ", 선택한 방 이름: " + room + "]");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 상태 : status");
        System.out.println("> 게임 초기화 : clear");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예) move b2 b3");
        final String input = scanner.nextLine();

        return Arrays.stream(input.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }
}
