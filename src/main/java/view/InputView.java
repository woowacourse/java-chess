package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SEPERATOR = ",";

    public static String getStartOrEnd() {
        return SCANNER.nextLine();
    }

    public static String getSourcePosition() {
        System.out.println("소스 위치를 입력하세요.((ex)1,2) end를 입력하면 게임 종료");
        return SCANNER.nextLine().trim().toLowerCase();

    }

    public static String getTargetPosition() {
        System.out.println("타깃 위치를 입력하세요.((ex)1,1) end를 입력하면 게임 종료");
        return SCANNER.nextLine().trim().toLowerCase();

    }

}
