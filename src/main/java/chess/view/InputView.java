package chess.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int printRoomId(List<Integer> id) {
        String rooms = id.stream().map(value -> Integer.toString(value)).collect(Collectors.joining(", "));
        System.out.println(
                "> 체스 게임에 오신 것을 환영합니다. 입장하시려는 게임방의 id를 입력해주세요.\n"
                        + "> 현재 존재하는 게임방 id: " + rooms + "\n"
                        + "> 새 게임을 원하신다면 0을 입력해주세요.");
        id.add(0);
        int input;
        do {
            input = Integer.parseInt(scanner.nextLine());
        }while(!id.contains(input));
        return input;
    }

    public static String readCommand() {
        String input = scanner.nextLine();
        checkBlank(input);
        return input;
    }


    private static void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백을 입력할 수 없습니다.");
        }
    }
}
