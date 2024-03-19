package chess.view;

import java.util.Objects;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public GameCommand readGameCommand() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
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
