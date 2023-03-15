package chess.view;

import static chess.controller.ChessExecuteCommand.END;
import static chess.controller.ChessExecuteCommand.START;
import static java.lang.String.format;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readChessExecuteCommand() {
        System.out.println(format("게임 시작은 %s, 종료는 %s 명령을 입력하세요.", START, END));
        return scanner.nextLine();
    }
}
