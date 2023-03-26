package chess.view.input;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RoomInputView {
    private static final String DELIMITER = " ";
    private static final int LIMIT = -1;

    private final Scanner scanner;

    public RoomInputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readCommand(final String user, final String room) {
        System.out.println("[로그인 한 유저: " + user + ", 선택한 방 이름: " + room + "]");
        System.out.println("> 방 조회 : history");
        System.out.println("> 방 생성 : create");
        System.out.println("> 방 참가 : join");
        System.out.println("> 메인 화면 : end");

        final String input = scanner.nextLine();

        return Arrays.stream(input.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }
}
