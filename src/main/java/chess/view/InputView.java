package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chess.view.command.StartCommand;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public StartCommand askStartMessage() {
        System.out.printf("체스 게임을 시작합니다.%n"
                + "> 게임 시작 : start%n"
                + "> 게임 종료 : end%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n");
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
