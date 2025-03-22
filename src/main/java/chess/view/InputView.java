package chess.view;

import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public void start() {
        System.out.println("체스 게임 시작!");
    }

    public List<Position> readPosition() {
        System.out.println("이동할 위치를 입력해주세요 ex) d4 d5");
        String s = readLine();
        String[] split = s.split(" ");
        Column startCol = Column.from(split[0].substring(0, 1));
        Row startRow = Row.from(split[0].substring(1, 2));

        Column arrivalCol = Column.from(split[1].substring(0, 1));
        Row arrivalRow = Row.from(split[1].substring(1, 2));
        return List.of(new Position(startCol, startRow), new Position(arrivalCol, arrivalRow));
    }

    private String readLine() {
        return SCANNER.nextLine();
    }
}
