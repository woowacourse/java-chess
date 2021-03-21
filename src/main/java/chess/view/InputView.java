package chess.view;

import chess.controller.dto.CommandDTO;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static CommandDTO getCommand() {
        String commandLineInput = SCANNER.nextLine();
        return new CommandDTO(commandLineInput);
    }
}
