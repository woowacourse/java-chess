package chess.view;

import chess.domain.TeamColor;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String[] readMoveCommand(String teamName) {
        System.out.println(teamName + " 차례입니다.");
        System.out.println("이동 명령을 입력해주세요.");
        System.out.println("형식: 시작위치 목적위치 ex) b1 b3");

        String input = scanner.nextLine();

        return input.split(" ");
    }
}
