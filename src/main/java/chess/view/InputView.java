package chess.view;

import java.util.Scanner;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static String inputCurrentCoordinate() {
        System.out.println("움직이고 싶은 말의 좌표를 입력하시오. (x,y)");
        return scanner.nextLine();
    }

    public static String inputDestinationCoordinate() {
        System.out.println("도착지의 좌표를 입력하시오. (x,y)");
        return scanner.nextLine();
    }
}
