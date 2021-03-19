package chess.domain;

import chess.domain.board.position.Position;
import chess.manager.Menu;

public class MoveCommand {

    private final Menu move;
    private final Position source;
    private final Position target;

    public MoveCommand(Menu move, Position source, Position target) {
        this.move = move;
        this.source = source;
        this.target = target;
    }

    public static MoveCommand of(String input) {
        String[] splitInput = input.split(" ");
        validateCommandSize(splitInput);
        validateMoveCommand(splitInput[0]);
        Position source = new Position(splitInput[1]);
        Position target = new Position(splitInput[2]);

        return new MoveCommand(Menu.of(splitInput[0]), source, target);
    }

    private static void validateCommandSize(String[] splitInput) {
        if (splitInput.length != 3) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void validateMoveCommand(String input) {
        if (Menu.isMove(input)) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    public Position source() {
        return source;
    }

    public Position target() {
        return target;
    }
}
