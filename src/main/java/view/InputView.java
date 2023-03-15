package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.\n> 게임 시작 : start\n> 게임 종료 : end\n> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String START = "start";
    private static final String INIT_INPUT_ERROR_MESSAGE = "[ERROR] 게임을 시작하려면 start를 입력해 주세요";

    public static void printGameStartMessage() {
        printMessage(GAME_START_MESSAGE);
        readInitialGameCommand();
    }

    public static void readInitialGameCommand() {
        String input = SCANNER.nextLine();
        validateInitialCommand(input);
    }
    
    public static void validateInitialCommand(String input){
        if(!input.equals(START)) {
            throw new IllegalArgumentException(INIT_INPUT_ERROR_MESSAGE);
        }
    }
    
    public static void printMessage(String message) {
        System.out.println(message);
    }
}
