package chess.view.input;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInputView {
    private static final String DELIMITER = " ";
    private static final int LIMIT = -1;

    private final Scanner scanner;

    public UserInputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readCommand(final String user, final String room) {
        System.out.println("[로그인 한 유저: " + user + ", 선택한 방 이름: " + room + "]");
        System.out.println("> 회원가입 : register 이름 - 예) register charlie");
        System.out.println("> 로그인 : login 이름 - 예) login charlie");
        System.out.println("> 로그아웃 : logout");
        System.out.println("> 종료 : end");

        final String input = scanner.nextLine();

        return Arrays.stream(input.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }
}
