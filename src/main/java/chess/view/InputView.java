package chess.view;

import java.util.Scanner;

public class InputView {

    public static void getChessInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("하이 체스 게임임 방가");
        System.out.println("이동할 위치");
        scanner.nextLine();
        System.out.println("이동 후 위치");
        scanner.nextLine();
    }
}
