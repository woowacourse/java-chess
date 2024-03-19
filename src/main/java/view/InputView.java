package view;

import java.util.Scanner;

public class InputView {
    private static final InputView INSTANCE = new InputView(new Scanner(System.in));

    private final Scanner scanner;

    private InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public Command readCommand() {
        System.out.println(String.format("체스 게임을 시작합니다.%n게임 시작은 start, 종료는 end 명령을 입력하세요."));
        String commandText = scanner.nextLine();

        return Command.findByText(commandText);
    }
}
