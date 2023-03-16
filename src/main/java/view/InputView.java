package view;

import java.util.Map;
import java.util.Scanner;

public class InputView {

    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";

    private static final Map<String, Command> commandMap = Map.of(START, new Start(), END, new End());

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public void printStartMessage() {
        System.out.printf("> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : %s\n"
            + "> 게임 종료 : %s\n"
            + "> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3\n", START, END, MOVE, MOVE);
    }

    public Command readCommand() {
        String input = scanner.nextLine();
        validateEmpty(input);

        String[] inputs = input.split(" ");
        return getCommand(input, inputs);
    }

    private Command getCommand(String input, String[] inputs) {
        if (inputs[0].equals(START)) {
            return new Start();
        }
        if (inputs[0].equals(END)) {
            return new End();
        }
        if (inputs[0].equals(MOVE)) {
            return new Move(input);
        }
        throw new IllegalArgumentException("start, end, move만 입력가능합니다.");
    }

    private void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 문지입니다.");
        }
    }
}
