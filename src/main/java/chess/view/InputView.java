package chess.view;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void inputStartCommand() {
        String command = SCANNER.nextLine();
        if (!Command.isStart(command)) {
            throw new IllegalArgumentException();
        }
    }


    // move b2 b3
    public static List<Position> inputNextCommand() {
        String input = SCANNER.nextLine();

        StringTokenizer inputTokenizer = new StringTokenizer(input);
        String command = inputTokenizer.nextToken();

        if (Command.isStart(command)) {
            throw new IllegalArgumentException();
        }
        if (Command.isEnd(command)) {
            return new ArrayList<>();
        }

        List<Position> positions = new ArrayList<>();
        if (Command.isMove(command) && inputTokenizer.countTokens() == 2) {
            positions.add(PositionConverter.generate(inputTokenizer.nextToken()));
            positions.add(PositionConverter.generate(inputTokenizer.nextToken()));
        }
        return positions;
    }

}
