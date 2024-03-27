package chess.view;

import chess.domain.position.Position;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern POSITION_PATTERN = Pattern.compile("^[a-h][1-8]$");

    private final Scanner scanner = new Scanner(System.in);

    public Command readCommand() {
        return Command.from(scanner.nextLine());
    }

    public Position resolvePosition(String rawPosition) {
        if (POSITION_PATTERN.matcher(rawPosition).matches()) {
            int file = rawPosition.charAt(0) - 'a' + 1;
            int rank = Character.getNumericValue(rawPosition.charAt(1));
            return Position.of(file, rank);
        }
        throw new IllegalArgumentException("말의 위치를 a1, h8과 같은 형태로 입력해 주세요.");
    }
}
