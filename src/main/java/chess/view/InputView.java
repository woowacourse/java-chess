package chess.view;

import java.util.Scanner;

public class InputView {

    private static final String START_MESSAGE = "체스 게임을 시작합니다.";
    private static final Scanner scanner = new Scanner(System.in);
    public void printStartChess(){
        System.out.println(START_MESSAGE);
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }
    public String requestCommend(){
        return scanner.nextLine();
    }
}
