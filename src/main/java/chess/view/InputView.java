package chess.view;

import chess.model.position.Column;
import chess.model.position.Position;
import chess.model.position.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static List<Position> readMovePositions() {
        System.out.println("<srcPosition> <destPosition> 형태로 입력해주세요");
        System.out.println("ex) 1,2 3,5");

        String input = sc.nextLine();
        String[] parsedInput = input.split(" ", -1);
        List<Position> positions = new ArrayList<>();
        for (String text : parsedInput) {
            Row row = Row.findByNumber(Integer.parseInt(text.charAt(0) + ""));
            Column column = Column.findByNumber(Integer.parseInt(text.charAt(2) + ""));
            positions.add(new Position(row, column));
        }
        return positions;
    }
}
