package chess.view;

import java.awt.Point;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public GameCommand getGameCommand() {
        return GameCommand.getGameCommand(scanner.next());
    }

    public Point getPosition() {
        String input = scanner.next();
        validateEmpty(input);
        return PointConverter.convert(input);
    }

    private void validateEmpty(final String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("이동 명령어의 위치 값이 필요합니다.");
        }
    }
}
