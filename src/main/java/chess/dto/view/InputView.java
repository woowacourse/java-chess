package chess.dto.view;

import static camp.nextstep.edu.missionutils.Console.*;

import java.util.List;

import chess.Command;
import chess.Position;
import chess.dto.MoveRequest;

public class InputView {

    private InputView() {
    }

    public static Command inputCommandInStart() {
        return Command.startEnd(readLine());
    }

    public static MoveRequest inputCommandInGaming() { // RequestDto
        String input = readLine();
        Command command = Command.endMove(input);

        List<String> inputs = List.of(input.split(" "));

        Position source = new Position(inputs.get(1));
        Position target = new Position(inputs.get(2));
        return new MoveRequest(command, source, target);
    }

}
