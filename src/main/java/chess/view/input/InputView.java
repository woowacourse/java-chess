package chess.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String readGameCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        String input = scanner.nextLine();
        validateInput(input);
        return input;
    }

    private void validateInput(String command) {
        if (Objects.isNull(command) || command.isBlank()) {
            throw new IllegalArgumentException("입력값은 공백일 수 없습니다.");
        }
    }

    public GameArguments readMoveArguments() {
        String input = scanner.nextLine();
        List<String> inputs = Arrays.stream(input.split(" ")).toList();
        GameCommand moveCommand = GameCommand.createInProgress(inputs.get(0));
        if (moveCommand.isMove()) {
            return new GameArguments(moveCommand, MoveArguments.from(inputs));
        }
        return new GameArguments(moveCommand, null);
    }
}
