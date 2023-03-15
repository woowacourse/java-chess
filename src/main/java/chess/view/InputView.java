package chess.view;

import java.util.Scanner;

public class InputView {

    private static final String GAME_COMMAND_REQUEST = String.format("게임 시작은 %s, 종료는 %s 명령을 입력하세요.",
            Command.START.getAnswer(), Command.END.getAnswer());
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Command requestGameCommand() {
        System.out.println(GAME_COMMAND_REQUEST);
        return Command.findByAnswer(scanner.nextLine());
    }

}
