package chess.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputStart() {
        System.out.println("이동할 말의 위치 입력 (ex. 1A)");
        return scanner.nextLine();
    }

    public String inputEnd() {
        System.out.println("\n이동할 말의 도착점 입력 (ex. 1A)");
        return scanner.nextLine();
    }
}
