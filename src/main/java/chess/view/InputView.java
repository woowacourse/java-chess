package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public GameCommand readGameCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        String command = scanner.nextLine();
        validate(command);
        return GameCommand.from(command);
    }

    private void validate(String command) {
        if (Objects.isNull(command) || command.isBlank()) {
            throw new IllegalArgumentException("입력값은 공백일 수 없습니다.");
        }
    }
}
