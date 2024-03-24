package chess.view;

import chess.Command;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Command getCommand() {
        String command = scanner.next();
        return Command.of(command);
    }

    public Position getMovePosition() {
        String movePosition = scanner.next();
        String fileName = movePosition.substring(0, 1);
        String rankName = movePosition.substring(1, 2);
        return new Position(File.of(fileName), Rank.of(rankName));
    }
}
