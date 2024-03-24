package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import chess.domain.board.Coordinate;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    //TODO: 메서드 라인 수 개선
    public boolean readStartCommand() {
        String command = scanner.nextLine().trim();

        if ("start".equals(command)) {
            return true;
        }

        if ("end".equals(command)) {
            return false;
        }

        throw new IllegalArgumentException("잘못된 입력입니다. start와 end 중 하나를 입력해주세요.");
    }

    public MoveCommand readMoveCommand() {
        String input = scanner.nextLine().trim();

        if ("end".equals(input)) {
            return new MoveCommand(true, null, null);
        }

        return createMoveCommand(input);
    }

    // TODO: 해당 책임의 적절한 위치를 생각
    private MoveCommand createMoveCommand(String input) {
        List<String> splitInputs = Arrays.stream(input.split(" "))
                .toList();

        String command = splitInputs.get(0);
        if (!"move".equals(command)) {
            throw new IllegalArgumentException("존재하지 않는 명령어입니다.");
        }

        if (splitInputs.size() != 3) {
            throw new IllegalArgumentException("잘못된 입력입니다. move source target 형식으로 입력해주세요. ex) move a2 a4");
        }

        String sourceInput = splitInputs.get(1);
        String targetInput = splitInputs.get(2);
        Coordinate source = createCoordinate(sourceInput);
        Coordinate target = createCoordinate(targetInput);

        return new MoveCommand(false, source, target);
    }

    private Coordinate createCoordinate(String coordinateInput) {
        List<String> splitInputs = Arrays.stream(coordinateInput.split("")).toList();
        if (splitInputs.size() != 2) {
            throw new IllegalArgumentException("올바른 형식의 좌표를 입력해주세요. ex) a2");
        }

        char file = splitInputs.get(0).charAt(0);
        int rank = Integer.parseInt(splitInputs.get(1));
        return new Coordinate(rank, file);
    }
}
