package chess.view;

import chess.Color;
import java.util.Scanner;

public class InputView {

    Scanner scanner = new Scanner(System.in);

    public String inputMoveCommand(Color turn) {
        System.out.println(String.format("""
                현재 턴: %s
                이동시킬 기물의 위치와 목표 위치를 입력해 주세요. (종료Q)
                ex) 7A, 6A
                """, turn.name()));
        return scanner.nextLine();
    }
}
