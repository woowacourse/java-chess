package view;

import java.util.Scanner;

public class InputView {

    public static final String START = "start";
    public static final String END = "end";
    private static final Scanner sc = new Scanner(System.in);

    public static String responseUserCommand() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 " + START + ", 종료는 " + END + " 명령을 입력하세요.");
        String input = sc.nextLine();
        validateUserCommand(input);
        return input;
    }

    private static void validateUserCommand(String input) {
        validateNullCheck(input);
        validateNotAllowCommand(input);
    }

    private static void validateNullCheck(String input) {
        if(input == null || input.isEmpty()){
            throw new IllegalArgumentException("[ERROR] 게임 명령에 공백을 입력할 수 없습니다.");
        }
    }

    private static void validateNotAllowCommand(String input) {
        if(!(input.equals(START) || input.equals(END))){
            throw new IllegalArgumentException("[ERROR] start, end 이외의 문자는 입력할 수 없습니다.");
        }
    }
}
