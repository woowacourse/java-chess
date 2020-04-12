package chess.view;

import chess.dto.ContinueCommandDto;
import chess.dto.StartCommandDto;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public StartCommandDto askStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        try {
            String input = SCANNER.nextLine();
            return new StartCommandDto(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askStartCommand();
        }
    }

    @Override
    public ContinueCommandDto askContinueCommand() {
        try {
            String input = SCANNER.nextLine();
            return new ContinueCommandDto(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askContinueCommand();
        }
    }
}
