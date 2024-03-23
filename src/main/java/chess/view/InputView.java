package chess.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern COMMAND_REGEX = Pattern.compile("(move)|(start)|(end)");
    private static final Pattern POSITION_REGEX = Pattern.compile("([a-h][1-8]\\s+[a-h][1-8])");

    private final Scanner scanner = new Scanner(System.in);

    public Commend readCommend() {
        String input = scanner.next();
        if (!COMMAND_REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException("입력한 체스 게임 명령어가 올바르지 않습니다.");
        }
        return Commend.inputToCommend(input);
    }

    public MoveRequestDto readPositions() {
        String input = scanner.nextLine().strip();
        if (!POSITION_REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException("입력한 source위치 target위치 형식이 올바르지 않습니다.");
        }

        return new MoveRequestDto(
                input.substring(0, 1),
                input.substring(1, 2),
                input.substring(3, 4),
                input.substring(4, 5)
        );
    }
}
