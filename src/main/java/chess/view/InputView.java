package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public GameStartCommand readGameStartCommand() {
        System.out.println("체스 게임을 시작합니다.");
        String rawInput = read(String.format("게임 시작은 %s, 종료는 %s 명령을 입력하세요.",
                GameStartCommand.START, GameStartCommand.END));

        return GameStartCommand.map(rawInput);
    }

    private String read(final String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
