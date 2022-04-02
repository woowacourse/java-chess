package chess.view;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class MoveCommand {

    private static final String COMMAND_DELIMITER = " ";
    private static final String LOCATION_DELIMITER = "";
    private static final int SOURCE = 0;
    private static final int TARGET = 1;
    private static final int RANK = 1;
    private static final int FILE = 0;

    private final List<Position> moves;

    public MoveCommand(String input) {
        this.moves = toMoveFormat(input);
    }

    private List<Position> toMoveFormat(String input) {
        List<Position> moves = new ArrayList<>();
        List<String> moveInfo = List.of(input.split(COMMAND_DELIMITER));
        for (int index = 1; index < moveInfo.size(); index++) {
            moves.add(Position.of(moveInfo.get(index).split(LOCATION_DELIMITER)[RANK],
                    moveInfo.get(index).split(LOCATION_DELIMITER)[FILE]));
        }
        return moves;
    }

    public Position getSource() {
        return moves.get(SOURCE);
    }

    public Position getTarget() {
        return moves.get(TARGET);
    }
}
