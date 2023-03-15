package chessgame.view;

import java.util.Optional;
import java.util.Scanner;

import chessgame.controller.State;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        String input;
        do {
            input = scanner.nextLine();
        } while (isState(input));
        return input;
    }

    private boolean isState(String input) {
        Optional<State> state = State.getState(input.split(" ")[0]);

        if (state.isEmpty()) {
            System.out.println("start, move, end 로만 입력해주세요");
            return true;
        }
        if (state.get() == State.MOVE) {
            return checkPoint(input);
        }
        return false;
    }

    private boolean checkPoint(String input) {
        String source = input.split(" ")[1];
        String target = input.split(" ")[2];
        String pattern = "[a-h]{1}[1-8]{1}";
        if (source.matches(pattern) && target.matches(pattern)) {
            return false;
        }
        System.out.println("이동 좌표는 a-h 1-8 로 입력하여야합니다.");
        return true;
    }

}
