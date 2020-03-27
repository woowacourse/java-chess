package chess.view;

import chess.controller.dto.MoveCommandDto;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";

    @Override
    public boolean askStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        try {
            String input = SCANNER.nextLine();
            validateStartCommand(input);
            return runFlag(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askStartCommand();
        }
    }

    private void validateStartCommand(String input) {
        if (!input.equals(START_COMMAND) && !input.equals(END_COMMAND)) {
            throw new IllegalArgumentException("start 또는 end 를 입력해주세요.");
        }
    }

    private boolean runFlag(String input) {
        return input.equals(START_COMMAND);
    }

    @Override
    public MoveCommandDto askRunCommand() {
        try {
            String input = SCANNER.nextLine();
            if (input.contains(MOVE_COMMAND)) {
                return new MoveCommandDto(input);
            }
            return new MoveCommandDto(input); //바꾸기
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askRunCommand();
        }
    }
}
