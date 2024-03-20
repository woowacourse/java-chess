package chess.view;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        String command = SCANNER.nextLine();

        List<String> commands = Arrays.asList(command.split(" "));
        List<Position> positions = new ArrayList<>();

        if (Command.isStart(commands.get(0))) {
            throw new IllegalArgumentException();
        }

        if (Command.isMove(commands.get(0)) && commands.size() == 3) {
            positions.add(PositionConverter.generate(commands.get(1)));
            positions.add(PositionConverter.generate(commands.get(2)));
        }

        return positions;
    }

}
