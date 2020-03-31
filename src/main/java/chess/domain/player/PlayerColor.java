package chess.domain.player;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import chess.domain.board.Position;

public enum PlayerColor {

    BLACK("BLACK", String::toUpperCase, Position::horizontalFlip),
    WHITE("WHITE", String::toLowerCase, Function.identity()),
    NONE("NONE", Function.identity(), Function.identity());

    private final String name;
    private final Function<String, String> nameDecider;
    private final Function<Position, Position> positionReviser;

    PlayerColor(String name, Function<String, String> nameDecider, Function<Position, Position> positionReviser) {
        this.name = name;
        this.nameDecider = nameDecider;
        this.positionReviser = positionReviser;
    }

    public String decideName(String name) {
        return nameDecider.apply(name);
    }

    public List<Position> reviseInitialPositions(List<Position> positions) {
        return positions.stream()
                .map(positionReviser)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }
}
