package chess.domain.piece;

import util.NullChecker;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Color {
    BLACK("BLACK", String::toUpperCase, cheatSheets -> cheatSheets.stream().map(Direction::reverse).collect(Collectors.toList())),
    WHITE("WHITE", String::toLowerCase, cheatSheets -> cheatSheets);

    private final String name;
    private final Function<String, String> typeNameChanger;
    private final Function<List<Direction>, List<Direction>> directionChanger;

    Color(String name, Function<String, String> typeNameChanger, Function<List<Direction>, List<Direction>> directionChanger) {
        this.name = name;
        this.typeNameChanger = typeNameChanger;
        this.directionChanger = directionChanger;
    }

    public String getName() {
        return this.name;
    }

    public String getApplyTypeName(Type type) {
        NullChecker.validateNotNull(type);
        return typeNameChanger.apply(type.getName());
    }

    public List<Direction> getChangeDirection(List<Direction> directions) {
        NullChecker.validateNotNull(directions);
        return directionChanger.apply(directions);
    }
}
