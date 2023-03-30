package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readCommandAndParameters() {
        return Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(" "))
                .collect(Collectors.toUnmodifiableList());
    }

    public static int readRoomNumber() {
        System.out.println("참여할 방 번호를 입력하세요, 새로운 게임 방을 만드려면 0을 입력하세요");
        String rawRoomNumber = scanner.nextLine().trim();
        validateIsNumber(rawRoomNumber);
        return Integer.parseInt(rawRoomNumber);
    }

    private static void validateIsNumber(final String rawRoomNumber) {
        if (rawRoomNumber.chars().allMatch(Character::isDigit)) {
            return;
        }
        throw new IllegalArgumentException("방 번호는 숫자만 입력 가능합니다.");
    }
}
