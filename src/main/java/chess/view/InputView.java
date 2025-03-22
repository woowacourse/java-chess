package chess.view;

import chess.board.Position;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static MoveCommand inputMoveCommand() {
        System.out.println("움직이려는 기물의 시작 위치를 입력해주세요. (예: move 7,1 5,1)");
        String input = scanner.nextLine();

        List<String> parsed = Arrays.stream(input.split(" ", -1)).toList();
        if (parsed.size() != 3) {
            throw new IllegalArgumentException(input + ": 형식에 맞게 입력해주세요.");
        }

        Position source = createPosition(parsed.get(1));
        Position destination = createPosition(parsed.get(2));
        return new MoveCommand(source, destination);
    }

    private static Position createPosition(String parsed) {
        List<Integer> positions = Arrays.stream(parsed.split(",", -1))
                .map(String::strip)
                .map(Integer::parseInt)
                .toList();
        validatePositionSize(positions);

        return new Position(positions.get(0), positions.get(1));
    }

    private static void validatePositionSize(List<Integer> positions) {
        if (positions.size() != 2) {
            throw new IllegalArgumentException(positions + ": 입력 형식을 맞춰주세요.");
        }
    }
}
