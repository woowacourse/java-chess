package chess.dto.view;

import java.util.List;

import chess.constant.Command;
import chess.domain.board.Position;
import chess.dto.MoveRequest;
import chess.dto.NotMoveRequest;
import chess.dto.Request;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Command inputStartCommand() {
        return Command.startEnd(SCANNER.nextLine());
    }

    public static Request inputCommandInGaming() {
        String input = SCANNER.nextLine();
        Command command = Command.endMove(input);
        if (command == Command.END) {
            return new NotMoveRequest(command);
        }

        if (command == Command.STATUS) {
            return new NotMoveRequest(command);
        }
        List<String> inputs = List.of(input.split(" "));

        Position source = new Position(inputs.get(1));
        Position target = new Position(inputs.get(2));
        return new MoveRequest(command, source, target);
    }

}
