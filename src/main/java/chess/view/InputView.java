package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readMovement() {
        System.out.println("음직일 기물의 위치와 목적지를 입력하세요");
        return scanner.nextLine();
    }
}
