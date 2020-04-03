package chess.domain.player;

import chess.domain.board.Position;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PlayerColor {

    BLACK("BLACK", String::toUpperCase, Position::horizontalFlip, turn -> turn % 2 == 1),
    WHITE("WHITE", String::toLowerCase, Function.identity(), turn -> turn % 2 == 0),
    NONE("", Function.identity(), Function.identity(), turn -> false);

    private final String name;
    private final Function<String, String> nameDecider;
    private final Function<Position, Position> positionReviser;
    private final Predicate<Integer> playerOf;

    PlayerColor(String name, Function<String, String> nameDecider,
                Function<Position, Position> positionReviser, Predicate<Integer> playerOf) {
        this.name = name;
        this.nameDecider = nameDecider;
        this.positionReviser = positionReviser;
        this.playerOf = playerOf;
    }

    public static PlayerColor playerOf(int turn) {
        return Stream.of(values())
                .filter(color -> color.playerOf.test(turn))
                .findFirst()
                .orElseThrow(AssertionError::new);
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
