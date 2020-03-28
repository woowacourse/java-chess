package chess.domain.piece;

import chess.domain.movement.Direction;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import util.NullChecker;

public enum Color {
    BLACK("BLACK", "WHITE", String::toUpperCase,
        cheatSheets -> cheatSheets.stream().map(Direction::reverse).collect(Collectors.toList())),
    WHITE("WHITE", "BLACK", String::toLowerCase, cheatSheets -> cheatSheets);

    private final String name;
    private final String nextTurnName;
    private final Function<String, String> typeNameChanger;
    private final Function<List<Direction>, List<Direction>> directionChanger;

    Color(String name, String nextTurnName, Function<String, String> typeNameChanger,
        Function<List<Direction>, List<Direction>> directionChanger) {
        this.name = name;
        this.nextTurnName = nextTurnName;
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

    public Color nextTurnIfNotMySelf() {
        return Arrays.stream(Color.values())
            .filter(color -> this.nextTurnName.equals(color.name))
            .findFirst()
            .orElse(this);
    }
}
