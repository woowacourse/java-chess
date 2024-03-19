package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chess.view.command.StartCommand;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public StartCommand askStartMessage() {
        System.out.printf("체스 게임을 시작합니다.%n" + "게임 시작은 start, 종료는 end 명령을 입력하세요.%n");
        return StartCommand.of(input());
    }

    private String input() {
        try {
            return READER.readLine();
        } catch (IOException ignored) {
        }
        return "";
    }
}
