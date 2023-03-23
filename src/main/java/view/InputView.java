package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INVALID_INPUT_ERROR_MESSAGE = "입력이 잘못 되었습니다. 다시 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);

    public void printStartInformation() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임방 입장 : enter");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public List<String> getCommand() {
        final String[] inputs = scanner.nextLine().split(" ");
        if (inputs.length > 3) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
        return Arrays.stream(inputs).collect(Collectors.toList());
    }

    public String getBoardId() {
        System.out.println("체스 방을 입력해주세요.");
        final String input = scanner.nextLine();
        checkBlank(input);
        return input;
    }

    private void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
    }
}
