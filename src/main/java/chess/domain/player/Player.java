package chess.domain.player;

import chess.domain.board.Position;

import java.util.function.Function;

public enum Player {

    BLACK("BLACK", String::toUpperCase, Position::horizontalFlip),
    WHITE("WHITE", String::toLowerCase, Function.identity());

    private String name;
    private Function<String, String> nameDecider;
    private Function<Position, Position> positionReviser;

    Player(String name, Function<String, String> nameDecider, Function<Position, Position> positionReviser) {
        this.name = name;
        this.nameDecider = nameDecider;
        this.positionReviser = positionReviser;
    }

    public String decideName(String name) {
        return nameDecider.apply(name);
    }

    public Position reviseInitialPosition(Position position) {
        return positionReviser.apply(position);
    }

    public String getName() {
        return name;
    }
}
