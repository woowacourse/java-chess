package chess.domain.player;

import java.util.function.Function;

import chess.domain.board.Position;

public enum Player {

    BLACK(String::toUpperCase, Position::horizontalFlip),
    WHITE(String::toLowerCase, Function.identity());

    private Function<String, String> nameDecider;
    private Function<Position, Position> positionReviser;

    Player(Function<String, String> nameDecider,
            Function<Position, Position> positionReviser) {
        this.nameDecider = nameDecider;
        this.positionReviser = positionReviser;
    }

    public String decideName(String name) {
        return nameDecider.apply(name);
    }

    public Position reviseInitialPosition(Position position) {
        return positionReviser.apply(position);
    }
}
