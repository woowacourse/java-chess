package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readRoomCommand() {
        System.out.println("새로운 게임 : new");
        System.out.println("기존의 게임 : enter 방 번호  (예시) enter 14");
        return List.of(scanner.nextLine().split(" "));
    }

    public List<String> readGameCommand() {
        System.out.println("게임 시작     : start");
        System.out.println("기물 이동     : move source위치 target위치  (예시) move b2 b3");
        System.out.println("기물 점수 상태 : status");
        System.out.println("게임 종료     : end");
        return List.of(scanner.nextLine().split(" "));
    }
}
